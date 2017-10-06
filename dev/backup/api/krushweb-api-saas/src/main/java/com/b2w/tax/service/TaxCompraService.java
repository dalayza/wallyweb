package com.b2w.tax.service;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.b2w.tax.calculator.ImpostosCompraOrmsWrapper;
import com.b2w.tax.dao.ImpostosCompraOrmsRepository;
import com.b2w.tax.dao.NbmEstadoRepository;
import com.b2w.tax.exception.TaxException;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.Icms;
import com.b2w.tax.model.ImpostoCompraOrms;
import com.b2w.tax.model.TipoFornecedor;
import com.b2w.tax.model.database.Nbm;
import com.b2w.tax.service.util.TaxUtil;

@Service
@DependsOn(value = "icmsNbmEstadoRepository")
public class TaxCompraService {

    private static final Logger logger = Logger.getLogger(TaxCompraService.class);

    @Autowired
    private NbmService nbmService;

    @Autowired
    private NbmEstadoRepository nbmEstadoRepository;

    @Autowired
    private ImpostosCompraOrmsRepository impostosCompraOrmsRepository;

    @Autowired
    private IcmsService icmsService;

    @Autowired
    private TaxService taxService;

    public Float findCofinsPercent(Long ncm, Integer ncmSeq) throws TaxException {
        Float cofins = null;
        Nbm nbmFound = nbmService.findNbm(ncm, ncmSeq);
        if (null != nbmFound && null != nbmFound.getNbmPercCofins()) {
            cofins = nbmFound.getNbmPercCofins();
        } else {
            cofins = taxService.findParametrosFaturamento().getPafaPercCofins();
        }
        if (null == cofins) {
            throw new TaxNotFoundException(
                    "Não foi possível encontrar o COFINS para este NCM [" + ncm + "]");
        }
        return cofins;
    }

    public Float findPisPercent(Long ncm, Integer ncmSeq) throws TaxException {
        // TODO id cia está fixo (1)
        Float pis = null;
        Nbm nbmFound = nbmService.findNbm(ncm, ncmSeq);
        if (null != nbmFound && null != nbmFound.getNbmPercPis()) {
            pis = nbmFound.getNbmPercPis();
        } else {
            pis = taxService.findParametrosFaturamento().getPafaPercPis();
        }

        if (null == pis) {
            throw new TaxNotFoundException(
                    "Não foi possível encontrar o PIS para este NCM [" + ncm + "]");
        }
        return pis;
    }

    public Float findIpiPercent(Long ncm, Integer ncmSeq) throws TaxNotFoundException {

        return nbmService.findNbmForIpi(ncm, ncmSeq) //
                .map(Nbm::getNbmPercIpi) //
                .map(TaxUtil::getValueOrZero) //
                .orElse(new Float(0));
    }

    public Float findIvaPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino) {
        Float result = null;
        if (null != ncmSeq) {
            result = nbmEstadoRepository.findIvaByNbmAndUf(ncm, ncmSeq, ufOrigem, ufDestino,
                    new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
                    TaxUtil.sentidoEntrada);
        } else {
            result = nbmEstadoRepository.findIvaByNbmAndSeqExternoUf(TaxUtil.getNcmFromOrmsNcm(ncm),
                    TaxUtil.getSeqExternalFromNcm(ncm), ufOrigem, ufDestino,
                    new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
                    TaxUtil.sentidoEntrada);
        }

        return TaxUtil.getValueOrNumber(result, 0f);
    }

    @Cacheable(value = "impostoCompraOrmsCache", cacheManager = "cacheManager")
    public ImpostoCompraOrms findImpostosCompraOrms(Long ncm, Boolean icmsSt, String ufOrigem,
            String ufDestino, TipoFornecedor tipoFornecedor, Boolean optanteSimples)
            throws TaxException {
        try {
            logger.debug(MessageFormat.format("Buscando impostos compra {0} {1} {2} {3} {4}", ncm,
                    icmsSt, ufOrigem, ufDestino, tipoFornecedor, optanteSimples));
            if (icmsSt && !(tipoFornecedor.equals(TipoFornecedor.DISTRIBUIDOR)
                    || tipoFornecedor.equals(TipoFornecedor.INDUSTRIALIZADOR))) {
                throw new TaxNotFoundException(
                        "Quando o item possui icmsSt o tipoFornecedor deve ser DISTRIBUIDOR ou INDUSTRIALIZADOR. O valor ["
                                + tipoFornecedor + "] não é válido.");
            }
            int utilizacao = TaxUtil.getOrmsUtilizacao(icmsSt, ufOrigem.equals(ufDestino),
                    optanteSimples, tipoFornecedor);
            int cfop = TaxUtil.getOrmsCfop(icmsSt, ufOrigem.equals(ufDestino));
            List<? extends ImpostoCompraOrms> impostoCompras =
                    impostosCompraOrmsRepository.findImpostosCompraByUtilizacaoECfop(ncm.toString(),
                            ufDestino, utilizacao, cfop);
            if (null == impostoCompras || impostoCompras.size() == 0) {
                throw new TaxNotFoundException(
                        "Não foi possível encontrar os impostos de compra no ORMS");
            }

            ImpostoCompraOrms impostoCompra = impostoCompras.get(0);
            ImpostosCompraOrmsWrapper impostosCompraWrapper =
                    new ImpostosCompraOrmsWrapper(impostoCompra);
            impostosCompraWrapper.convertNullToZero();
            impostosCompraWrapper.convertBaseReduzidas();
            impostosCompraWrapper.calculaIcmsFinal();

            // se operação interestadual deve buscar a aliquota interestadual do
            // Umbrella, assume o ICMS menor
            if (!ufOrigem.equals(ufDestino)) {
                Float icmsInterestadual =
                        icmsService.findIcmsOnIcmsEstadosByUf(ufOrigem, ufDestino);
                if (null != icmsInterestadual && !ufOrigem.equals(ufDestino)) {
                    if (icmsInterestadual < impostoCompra.getAliqIcmsFinal()) {
                        impostoCompra.setAliqIcms(icmsInterestadual);
                        impostoCompra.setAliqIcmsFinal(icmsInterestadual);
                    }
                }
            }

            return impostoCompra;
        } catch (Exception ex) {
            logger.error("Problema ao buscar impostos compra do ORMS", ex);
            throw new TaxNotFoundException(
                    "Não foi possível buscar impostos compra " + ex.getMessage());
        }
    }

    public Float findIcmsSt(Long ncm, Integer seqNcm, String ufOrigem, String ufDestino) {
        return icmsService.findIcmsStOnNbmEstadosByNbmAndUf(ncm, seqNcm, ufOrigem, ufDestino,
                TaxUtil.sentidoEntrada);
    }

    public ImpostoCompraOrms findImpostosCompraUmbrella(Long ncm, Integer seqNcm, String ufOrigem,
            String ufDestino) throws TaxException {
        ImpostoCompraOrms result = new ImpostoCompraOrms();
        result.setAliqIpi(findIpiPercent(ncm, seqNcm));
        // TODO - No Umbrella não foi encontrado o campo base do IPI
        result.setAliqCofins(findCofinsPercent(ncm, seqNcm));
        result.setAliqPis(findPisPercent(ncm, seqNcm));
        result.setAliqIcms(findIcmsCompraPercent(ncm, seqNcm, ufOrigem, ufDestino));
        result.setBaseIcms(findBaseReduzidaPercent(ncm, seqNcm, ufOrigem, ufDestino));
        result.setAliqIcmsst(findIcmsSt(ncm, seqNcm, ufOrigem, ufDestino));
        // TODO - NO Umbrella não foi encontrada a red de base do ICMS ST
        result.setIva(findIvaPercent(ncm, seqNcm, ufOrigem, ufDestino));
        ImpostosCompraOrmsWrapper impostosCompraWrapper = new ImpostosCompraOrmsWrapper(result);
        impostosCompraWrapper.convertNullToZero();
        impostosCompraWrapper.convertBaseReduzidas();
        impostosCompraWrapper.calculaIcmsFinal();
        return result;
    }

    public Icms calculateValorIcmsSubstituicaoTributaria(Long ncm, Integer ncmSeq, Float valorBase,
            String ufOrigem, String ufDestino, boolean nationalSimple, boolean ignoreDifal)
            throws TaxException {
        String tipoConsumoDefault = "RESALE";

        return taxService.calculateICMSSubstituicaoTributaria(ncm, ncmSeq, valorBase, ufOrigem,
                ufDestino, TaxUtil.sentidoEntrada, nationalSimple, tipoConsumoDefault, ignoreDifal);
    }

    public Float findIcmsCompraPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.findIcmsPercent(ncm, ncmSeq, ufOrigem, ufDestino, TaxUtil.sentidoEntrada);
    }

    public Float findBaseReduzidaPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino) throws TaxException {
        return taxService.findBaseReduzidaPercent(ncm, ncmSeq, ufOrigem, ufDestino,
                TaxUtil.sentidoEntrada);
    }

    public boolean checkHasIcmsSt(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.checkHasIcmsSubstituicaoTributaria(ncm, ncmSeq, ufOrigem, ufDestino,
                TaxUtil.sentidoEntrada);
    }

    public boolean checkHasPpb(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.checkHasPpb(ncm, ncmSeq, ufOrigem, ufDestino, TaxUtil.sentidoEntrada);
    }

}
