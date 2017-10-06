package com.b2w.tax.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.b2w.tax.exception.TaxException;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.BaseReduzida;
import com.b2w.tax.model.Icms;
import com.b2w.tax.model.TipoImposto;
import com.b2w.tax.model.database.Nbm;
import com.b2w.tax.model.database.NbmEstado;
import com.b2w.tax.service.util.TaxUtil;

@Service
@DependsOn(value = "icmsNbmEstadoRepository")
public class TaxVendaService {

    Logger logger = Logger.getLogger(TaxVendaService.class);

    private final List<String> ufsBr = new ArrayList<String>(Arrays.asList(new String[] {"AC", "AL",
            "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA",
            "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "SP", "TO"}));

    @Autowired
    private NbmService nbmService;

    @Autowired
    private IcmsService icmsService;

    @Autowired
    private TaxService taxService;

    public Float findPisPercent(Long ncm, Integer ncmSeq) throws TaxException {
        // TODO id cia está fixo (1)
        Float pis = null;
        Nbm nbmFound = nbmService.findNbm(ncm, ncmSeq);
        if (null != nbmFound && null != nbmFound.getNbmPercPisSai()) {
            pis = nbmFound.getNbmPercPisSai();
        } else {
            pis = taxService.findParametrosFaturamento().getPafaPercPis();
        }

        if (null == pis) {
            throw new TaxNotFoundException(
                    "Não foi possível encontrar o PIS para este NCM [" + ncm + "]");
        }
        return pis;
    }

    public Float findCofinsPercent(Long ncm, Integer ncmSeq) throws TaxException {
        Float cofins = null;

        if (ncm == null) {
            throw new TaxNotFoundException("NCM value couldn't be empty.");
        }

        Nbm nbmFound = nbmService.findNbm(ncm, ncmSeq);
        if (null != nbmFound && null != nbmFound.getNbmPercCofinsSai()) {
            cofins = nbmFound.getNbmPercCofinsSai();
        } else {
            cofins = taxService.findParametrosFaturamento().getPafaPercCofins();
        }
        if (null == cofins) {
            throw new TaxNotFoundException(
                    "Não foi possível encontrar o COFINS para este NCM [" + ncm + "]");
        }
        return cofins;
    }

    public Float findIcmsStPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxNotFoundException {
        Float result = null;

        result = icmsService.findIcmsStOnNbmEstadosByNbmAndUf(ncm, ncmSeq, ufOrigem, ufDestino,
                TaxUtil.sentidoSaida);

        return result;
    }

    /**
     * TODO esta informação precisa ser validada, este pode não ser o local correto para buscar ICMS
     * ST
     * 
     * @param ncm
     * @param ncmSeq
     * @param ufOrigem
     * @param ufsDestino
     * @return
     * @throws TaxNotFoundException
     */
    public List<Icms> findIcmsStPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            LinkedList<String> ufsDestino) throws TaxException {
        List<Icms> icmsSts = new ArrayList<Icms>();
        List<NbmEstado> nbmEstados = icmsService.findIcmsOnNbmEstadosByNbmAndUfs(ncm, ncmSeq,
                ufOrigem, ufsDestino, TaxUtil.sentidoSaida);
        for (NbmEstado nbmEstado : nbmEstados) {
            if (null != nbmEstado.getNbmePercSubstrib()) {
                Icms icmsSt = new Icms(nbmEstado.getNbmeIdEstadoDe(),
                        nbmEstado.getNbmeIdEstadoPara(), nbmEstado.getNbmePercSubstrib());
                icmsSts.add(icmsSt);
            }
        }
        return icmsSts;
    }

    public List<Icms> removeWithNullTipoImposto(List<Icms> icmses) {
        Iterator<Icms> it = icmses.iterator();
        while (it.hasNext()) {
            Icms icms = it.next();
            if (icms.getTipoImposto() == null) {
                it.remove();
            }
        }
        return icmses;
    }

    public List<BaseReduzida> removeBrWithNullTipoImposto(List<BaseReduzida> bases) {
        Iterator<BaseReduzida> it = bases.iterator();
        while (it.hasNext()) {
            BaseReduzida base = it.next();
            if (base.getTipoImposto() == null) {
                it.remove();
            }
        }
        return bases;
    }

    public Float findFecpVendaPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.findFecpPercent(ncm, ncmSeq, ufOrigem, ufDestino, TaxUtil.sentidoSaida);
    }

    public Float findIcmsVendaPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.findIcmsPercent(ncm, ncmSeq, ufOrigem, ufDestino, TaxUtil.sentidoSaida);
    }

    public List<Icms> findIcmsVendasPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            LinkedList<String> ufsDestino) throws TaxException {
        return taxService.findIcmsesPercent(ncm, ncmSeq, ufOrigem, ufsDestino,
                TaxUtil.sentidoSaida);
    }

    public List<Icms> findIcmsInternosPercent(Long ncm, Integer ncmSeq) throws TaxException {
        return taxService.findIcmsInternosPercent(ncm, ncmSeq, TaxUtil.sentidoSaida);
    }

    public Float findBaseReduzidaPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino) throws TaxException {
        return taxService.findBaseReduzidaPercent(ncm, ncmSeq, ufOrigem, ufDestino,
                TaxUtil.sentidoSaida);
    }

    public List<BaseReduzida> findBasesReduzidasPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            LinkedList<String> ufsDestino) {
        return taxService.findBaseReduzidasPercent(ncm, ncmSeq, ufOrigem, ufsDestino,
                TaxUtil.sentidoSaida);
    }

    public List<BaseReduzida> findBaseReduzidaInternoPercent(Long ncm, Integer ncmSeq)
            throws TaxException {
        return taxService.findBaseReduzidaInternoPercent(ncm, ncmSeq, TaxUtil.sentidoSaida);
    }

    public Icms calculateIcmsSubstituicaoTributaria(Long ncm, Integer ncmSeq, Float valorBase,
            String ufOrigem, String ufDestino, boolean nationalSimple, String tipoConsumo,
            boolean ignoreDifal) throws TaxException {
        return taxService.calculateICMSSubstituicaoTributaria(ncm, ncmSeq, valorBase, ufOrigem,
                ufDestino, TaxUtil.sentidoSaida, nationalSimple, tipoConsumo, ignoreDifal);
    }

    public boolean checkHasIcmsSubstituicaoTributaria(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino) throws TaxException {
        return taxService.checkHasIcmsSubstituicaoTributaria(ncm, ncmSeq, ufOrigem, ufDestino,
                TaxUtil.sentidoSaida);
    }

    public boolean checkHasPpb(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino)
            throws TaxException {
        return taxService.checkHasPpb(ncm, ncmSeq, ufOrigem, ufDestino, TaxUtil.sentidoSaida);
    }

    /**
     * 
     * DIFAL - Diferencial de Alíquota do ICMS
     * 
     */
    public List<Icms> findIcmsDifal(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
            Float valorBase) throws TaxException {

        Float icmsInterestadualPercent = findIcmsVendaPercent(ncm, ncmSeq, ufOrigem, ufDestino);

        Float icmsComFecpDestinoPercent = findIcmsVendaPercent(ncm, ncmSeq, ufDestino, ufDestino);
        Float fecpDestinoPercent = findFecpVendaPercent(ncm, ncmSeq, ufDestino, ufDestino);
        Float icmsDestinoPercent = icmsComFecpDestinoPercent - fecpDestinoPercent;

        Float icmsInterestadual = valorBase * (icmsInterestadualPercent / 100f);
        Float icmsDestino = valorBase * (icmsDestinoPercent / 100f);
        Float fecpDestino = valorBase * (fecpDestinoPercent / 100f);

        Float difalOrigem = icmsService.calculateDifalOrigem(icmsDestino, icmsInterestadual);
        Icms icmsDifalOrigem = new Icms(ufOrigem, ufDestino, null, TipoImposto.ORIGEM);
        icmsDifalOrigem.setIcms(difalOrigem);
        icmsDifalOrigem.setValorBase(valorBase);

        Float difalDestino = icmsService.calculateDifalDestino(icmsDestino, icmsInterestadual);
        Icms icmsDifalDestino = new Icms(ufOrigem, ufDestino, null, TipoImposto.DESTINO);
        icmsDifalDestino.setIcms(difalDestino + fecpDestino);
        icmsDifalDestino.setValorBase(valorBase);

        return Arrays.asList(icmsDifalOrigem, icmsDifalDestino);
    }

}
