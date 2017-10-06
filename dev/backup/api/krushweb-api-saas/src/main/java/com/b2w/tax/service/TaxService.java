package com.b2w.tax.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.labs.core.dao.ItemAttributesQgRepository;
import com.b2w.labs.core.model.database.ItemAttributesQg;
import com.b2w.tax.calculator.ConsolidaValorImpostosVenda;
import com.b2w.tax.calculator.ImpostosCompraWrapper;
import com.b2w.tax.dao.FilialRegimeEspecialRepository;
import com.b2w.tax.dao.ItemRepository;
import com.b2w.tax.exception.TaxException;
import com.b2w.tax.exception.TaxModelException;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.exception.TaxServiceException;
import com.b2w.tax.model.BaseReduzida;
import com.b2w.tax.model.Icms;
import com.b2w.tax.model.ImpostoEstadoDestinoComparator;
import com.b2w.tax.model.ImpostosCompra;
import com.b2w.tax.model.ImpostosVenda;
import com.b2w.tax.model.ItemImpostos;
import com.b2w.tax.model.Iva;
import com.b2w.tax.model.NomenclaturaBrasileiraMercadorias;
import com.b2w.tax.model.Produto;
import com.b2w.tax.model.TipoCliente;
import com.b2w.tax.model.TipoImposto;
import com.b2w.tax.model.database.IcmsEstados;
import com.b2w.tax.model.database.IcmsNbmEstado;
import com.b2w.tax.model.database.NbmEstado;
import com.b2w.tax.model.database.ParametrosFaturamento;
import com.b2w.tax.service.util.TaxUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TaxService {

    private final List<String> ufsBr = new ArrayList<String>(Arrays.asList(new String[] {"AC", "AL",
            "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PR", "PB", "PA",
            "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SE", "SP", "TO"}));

    private static Logger logger = Logger.getLogger(TaxService.class);

    @Autowired
    private ItemAttributesQgRepository itemAttributesQgRepository;

    @Autowired
    private TaxCompraService taxCompraService;

    @Autowired
    private FilialRegimeEspecialRepository filialRegimeEspecialRepository;

    @Autowired
    private TaxVendaService taxVendaService;

    @Autowired
    private IcmsService icmsService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private NbmEstadoService nbmEstadoService;

    public ParametrosFaturamento findParametrosFaturamento() throws TaxException {
        ParametrosFaturamento p = new ParametrosFaturamento();
        p.setPafaPercCofins(7.6f);
        p.setPafaPercPis(1.65f);
        return p;
    }

    public Long findNcm(String sku) {
        ItemAttributesQg itemAttributesQg = itemAttributesQgRepository.findByItem(sku);
        if (null != itemAttributesQg && null != itemAttributesQg.getNcm()) {
            return new Long(itemAttributesQg.getNcm());
        } else {
            return null;
        }
    }

    public Map<String, Long> findNcmsMapByProduto(List<Produto> produtos) throws TaxException {
        List<String> skus = new ArrayList<String>();
        for (Produto produto : produtos) {
            if (null != produto.getSku()) {
                skus.add(produto.getSku().toString());
            }
        }
        return findNcmsMap(skus);
    }

    public Map<String, Long> findNcmsMap(List<String> skus) throws TaxException {

        Map<String, Long> ncmsMap = new HashMap<>();
        Iterator<String> it = skus.iterator();
        while (it.hasNext()) {
            String sku = it.next();
            Long ncm = findNcm(sku);

            if (ncm == null)
                throw new TaxNotFoundException("Não foram encontrados NCMs para alguns items.",
                        skus);

            logger.debug("NCM [" + ncm + "] encontrado para o item [" + sku + "]");
            ncmsMap.put(sku, ncm);
        }

        return ncmsMap;
    }

    public NomenclaturaBrasileiraMercadorias findNbm(String sku) throws TaxException {
        com.b2w.tax.model.database.Item item = itemRepository.findByItemIdItem(new BigInteger(sku));

        if (item == null) {
            throw new TaxNotFoundException("Item not found with SKU " + sku);
        } else if (item.getItemIdNbm() == null) {
            throw new TaxNotFoundException("Item don't have IdNbm - SKU " + sku);
        }

        return new NomenclaturaBrasileiraMercadorias(item.getItemIdNbm().longValue(),
                (item.getItemSeqNbm() != null ? item.getItemSeqNbm().intValue() : null));
    }

    public Map<String, NomenclaturaBrasileiraMercadorias> findNbmsMap(List<String> skus)
            throws TaxException {
        Map<String, NomenclaturaBrasileiraMercadorias> nbmsMap = new HashMap<>();

        Iterator<String> it = skus.iterator();
        while (it.hasNext()) {
            String sku = it.next();
            NomenclaturaBrasileiraMercadorias nbm = findNbm(sku);

            logger.debug("NBM [" + nbm.getNbm() + "] encontrado para o item [" + sku + "]");
            nbmsMap.put(sku, nbm);
        }

        return nbmsMap;
    }

    public ArrayList<Produto> convertJsonToListProduto(List<String> produtosJson)
            throws TaxException {
        logger.debug("Vai converter string para objeto Produto");
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        ObjectMapper om = new ObjectMapper();
        String lineBefore = "";
        for (String pJson : produtosJson) {
            try {
                String toConvert;
                if (pJson.endsWith("}")) {
                    toConvert = lineBefore + pJson;
                    lineBefore = "";
                } else {
                    lineBefore = pJson + ",";
                    continue;
                }
                logger.debug("Vai converter a string [" + toConvert + "] para Produto");
                Produto p = om.readValue(toConvert, Produto.class);
                produtos.add(p);
            } catch (Exception ex) {
                logger.error("Problema ao converter json para Produto", ex);
                throw new TaxModelException("Problema ao ler produto, verifique o json informado");
            }
        }
        return produtos;
    }

    /**
     *
     *
     */
    public class FindImpostosQueryBuilder {
        private List<Produto> produtos;
        private String ufOrigem;
        private TipoCliente tipoCliente;
        private boolean simplesNacional;
        private boolean useCompra;
        private List<String> uFsDestinos;
        private boolean icmsSubstituicaoTributaria;
        private String tipoConsumo;
        private boolean ignoreDifal;

        public FindImpostosQueryBuilder bySKUs(List<String> skus) {
            produtos = convertToListProdutoSemValorBase(skus);
            return this;
        }

        public FindImpostosQueryBuilder byProducts(List<Produto> produtos) {
            this.produtos = produtos;
            return this;
        }

        public FindImpostosQueryBuilder withUFOrigem(String ufOrigem) {
            this.ufOrigem = ufOrigem;
            return this;
        }

        public FindImpostosQueryBuilder withTipoCliente(TipoCliente tipoCliente) {
            this.tipoCliente = tipoCliente;
            return this;
        }

        public FindImpostosQueryBuilder withSimplesNacional(boolean simplesNacional) {
            this.simplesNacional = simplesNacional;
            return this;
        }

        public FindImpostosQueryBuilder withTipoConsumo(String tipoConsumo) {
            this.tipoConsumo = tipoConsumo;
            return this;
        }

        public FindImpostosQueryBuilder withCompra(boolean useCompra) {
            this.useCompra = useCompra;
            return this;
        }

        public FindImpostosQueryBuilder withUFsDestino(List<String> UFsDestinos) {
            uFsDestinos = UFsDestinos;
            return this;
        }

        public FindImpostosQueryBuilder withIcmsSubstituicaoTributaria(boolean icmsSt) {
            this.icmsSubstituicaoTributaria = icmsSt;
            return this;
        }

        public FindImpostosQueryBuilder withIgnoreDifal(boolean ignoreDifal) {
            this.ignoreDifal = ignoreDifal;
            return this;
        }

        public List<ItemImpostos> find() throws TaxException {

            if (ufOrigem == null || !ufsBr.contains(ufOrigem)) {
                throw new TaxException("O UF de origem informado é inválido [" + ufOrigem + "]");
            }

            return findImpostosByItems(produtos, ufOrigem,
                    (uFsDestinos != null ? uFsDestinos : ufsBr), useCompra,
                    icmsSubstituicaoTributaria, tipoCliente, simplesNacional, tipoConsumo,
                    ignoreDifal);
        }

        private List<Produto> convertToListProdutoSemValorBase(List<String> skus) {
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            for (String sku : skus) {
                Produto p = new Produto();
                p.setSku(new Long(sku));
                produtos.add(p);
            }
            return produtos;
        }
    }

    private List<ItemImpostos> findImpostosByItems(List<Produto> produtos, String ufOrigem,
            List<String> ufsDestino, boolean incluiCompra, boolean hasIcmsSubstituicaoTributaria,
            TipoCliente tipoCliente, boolean nationalSimple, String tipoConsumo,
            boolean ignoreDifal) throws TaxException {

        List<ItemImpostos> itensImpostos = new ArrayList<ItemImpostos>();

        // key: sku, value: ncm
        // Map<String, Long> ncms = findNcmsMapByProduto(produtos);
        List<String> skus = new ArrayList<>();
        produtos.forEach(p -> skus.add(p.getSku() + ""));
        Map<String, NomenclaturaBrasileiraMercadorias> nbms = findNbmsMap(skus);

        // TODO busca os impostos da tabela de Itens do Umbrella, remove de
        // itemAttributesQgs conforme encontrar

        // busca os impostos na tabela ICNE e NBM_ESTADO
        for (Produto produto : produtos) {
            String sku = produto.getSku().toString();
            Float valorBase = produto.getValorBase();

            Long ncm = new Long(nbms.get(sku).getNbm());
            Integer ncmSeq = new Integer(nbms.get(sku).getSequentialUmbrella());

            List<Icms> icmsVendas = findIcmsesPercent(ncm, ncmSeq, ufOrigem,
                    new LinkedList<String>(ufsDestino), TaxUtil.sentidoSaida);
            Icms icmsOrigem = null;
            if (hasIcmsSubstituicaoTributaria && "SP".equals(ufOrigem)) {
                icmsOrigem = new Icms(ufOrigem, ufOrigem, new Float(0), TipoImposto.ORIGEM);
            } else {
                icmsOrigem = new Icms(ufOrigem, ufOrigem,
                        findIcmsPercent(ncm, ncmSeq, ufOrigem, ufOrigem, TaxUtil.sentidoSaida),
                        TipoImposto.ORIGEM);
            }
            icmsVendas.add(icmsOrigem);
            List<BaseReduzida> baseReduzidas = findBaseReduzidasPercent(ncm, ncmSeq, ufOrigem,
                    new LinkedList<String>(ufsDestino), TaxUtil.sentidoSaida);
            BaseReduzida baseReduzidaOrigem = new BaseReduzida(ufOrigem, ufOrigem,
                    findBaseReduzidaPercent(ncm, ncmSeq, ufOrigem, ufOrigem, TaxUtil.sentidoSaida),
                    TipoImposto.ORIGEM);
            baseReduzidas.add(baseReduzidaOrigem);

            List<Iva> ivas = new ArrayList<Iva>();
            List<Icms> icmsStsCompra = new ArrayList<Icms>();
            List<Icms> icmsSubstituicoesTributariasVenda = new ArrayList<Icms>();

            for (String uf : ufsDestino) {
                Icms icmsDestino, icmsStVenda = null;

                if (hasIcmsSubstituicaoTributaria && ("SP".equals(ufOrigem) && "SP".equals(uf))) {
                    icmsDestino = new Icms(uf, uf, new Float(0), new Float(0), new Float(0),
                            TipoImposto.DESTINO);
                    icmsSubstituicoesTributariasVenda
                            .add(new Icms(uf, uf, new Float(0), new Float(0), new Float(0)));
                } else {

                    icmsDestino = new Icms(uf, uf,
                            findIcmsPercent(ncm, ncmSeq, uf, uf, TaxUtil.sentidoSaida),
                            TipoImposto.DESTINO);

                    Float percentageICMSSubstituicaoTributariaVenda =
                            taxVendaService.findIcmsStPercent(ncm, ncmSeq, ufOrigem, uf);

                    if (null != percentageICMSSubstituicaoTributariaVenda) {
                        icmsStVenda = taxVendaService.calculateIcmsSubstituicaoTributaria(ncm,
                                ncmSeq, valorBase, ufOrigem, uf, nationalSimple, tipoConsumo,
                                ignoreDifal);
                        icmsSubstituicoesTributariasVenda.add(icmsStVenda);
                    } else {
                        icmsSubstituicoesTributariasVenda.add(
                                new Icms(ufOrigem, uf, new Float(0), new Float(0), new Float(0)));
                    }

                }

                icmsVendas.add(icmsDestino);
                BaseReduzida baseReduzidaDestino = new BaseReduzida(uf, uf,
                        findBaseReduzidaPercent(ncm, ncmSeq, uf, uf, TaxUtil.sentidoSaida),
                        TipoImposto.DESTINO);
                baseReduzidas.add(baseReduzidaDestino);

                if (incluiCompra) {
                    Float ivaPercent = taxCompraService.findIvaPercent(ncm, ncmSeq, ufOrigem, uf);
                    if (null != ivaPercent) {
                        Iva iva = new Iva(ufOrigem, uf, ivaPercent, TipoImposto.INTERESTADUAL);
                        ivas.add(iva);
                    }

                    Float icmsStPercentCompra =
                            taxCompraService.findIcmsSt(ncm, ncmSeq, ufOrigem, uf);
                    if (null != icmsStPercentCompra) {
                        Icms icmsSt = taxCompraService.calculateValorIcmsSubstituicaoTributaria(ncm,
                                ncmSeq, valorBase, ufOrigem, uf, nationalSimple, ignoreDifal);
                        if (null != icmsSt)
                            icmsStsCompra.add(icmsSt);
                    }
                }
            }

            ImpostosVenda impostosVenda = new ImpostosVenda();
            impostosVenda.setCofinsPercent(taxVendaService.findCofinsPercent(ncm, ncmSeq));
            impostosVenda.setPisPercent(taxVendaService.findPisPercent(ncm, ncmSeq));

            impostosVenda.setIcmsVendas(taxVendaService.removeWithNullTipoImposto(icmsVendas));
            impostosVenda
                    .setBaseReduzidas(taxVendaService.removeBrWithNullTipoImposto(baseReduzidas));
            impostosVenda.setIcmsSts(icmsSubstituicoesTributariasVenda);

            if (TipoCliente.FISICA.equals(tipoCliente)
                    || TipoCliente.JURIDICA_ISENTO_IE.equals(tipoCliente)) {
                // para clientes pessoa física ou jurídica com isenção de IE o
                // ICMS a ser utilizado é o do destino
                // coloca ele como primeiro da lista
                Collections.sort(impostosVenda.getIcmsVendas(),
                        new ImpostoEstadoDestinoComparator());
                Collections.sort(impostosVenda.getBaseReduzidas(),
                        new ImpostoEstadoDestinoComparator());
            }
            if (valorBase != null) {
                impostosVenda.setValorBase(valorBase);
                ConsolidaValorImpostosVenda consolidaValorImpostosVenda =
                        new ConsolidaValorImpostosVenda(impostosVenda);
                consolidaValorImpostosVenda.calcular();
            }

            ItemImpostos itemImpostos;
            if (incluiCompra) {
                // pega os impostos de compra
                ImpostosCompra impostosCompra = new ImpostosCompra();

                impostosCompra.setCofinsPercent(taxCompraService.findCofinsPercent(ncm, ncmSeq));
                impostosCompra.setPisPercent(taxCompraService.findPisPercent(ncm, ncmSeq));
                impostosCompra.setIvas(ivas);
                impostosCompra.setIcmsSts(icmsStsCompra);

                if (null != valorBase) {
                    impostosCompra.setValorBase(valorBase);
                    ImpostosCompraWrapper impostosCompraWrapper =
                            new ImpostosCompraWrapper(impostosCompra);
                    impostosCompraWrapper.calculaValores();
                }
                itemImpostos = new ItemImpostos(new Long(sku), ncm, impostosVenda, impostosCompra);
            } else {
                itemImpostos = new ItemImpostos(new Long(sku), ncm, impostosVenda);
            }

            itensImpostos.add(itemImpostos);

        }

        return itensImpostos;
    }

    /**
     *
     * @param sentidos - E (entrada), S (saida) ou A (ambos)
     */
    public Float findIcmsPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
            List<String> sentidos) throws TaxException {
        Float result = null;

        result = icmsService.findIcmsOnNbmEstadosByNbmAndUf(ncm, ncmSeq, ufOrigem, ufDestino,
                sentidos);

        if (result == null) {
            IcmsNbmEstado icmsNbmEstado = icmsService.findIcmsNbmEstadoFromIcne(ncm, ncmSeq,
                    TaxUtil.ID_CIA, ufOrigem, ufDestino, sentidos);

            if (TaxUtil.isAtivoAndHasIcms(icmsNbmEstado)) {
                result = icmsNbmEstado.getIcnePercIcms();
            } else {
                List<IcmsEstados> icmsEstados = icmsService.findIcmsEstadosByUfs(ufOrigem,
                        Arrays.asList(new String[] {ufDestino}));
                if (icmsEstados != null && icmsEstados.size() == 1) {
                    result = icmsEstados.get(0).getIcmePeIcms();
                }
            }
        }

        if (result == null) {
            throw new TaxNotFoundException(MessageFormat.format(
                    "Não foi possível determinar o ICMS Venda para a chave NCM, SEQ_NCM, UF_ORIGEM, UF_DESTINO, ID_CIA, DIA [{0}, {1}, {2}, {3}, {4}, {5}]",
                    ncm.toString(), ncmSeq, ufOrigem, ufDestino, TaxUtil.ID_CIA,
                    new Timestamp(System.currentTimeMillis())));
        }

        return result;
    }

    /**
     * @param sentidos - E (entrada), S (saida) ou A (ambos)
     */
    public Float findFecpPercent(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
            List<String> sentidos) throws TaxException {

        IcmsNbmEstado icmsNbmEstado = icmsService.findIcmsNbmEstadoFromIcne(ncm, ncmSeq,
                TaxUtil.ID_CIA, ufOrigem, ufDestino, sentidos);

        Float result = null;
        if (TaxUtil.isAtivoAndHasFecp(icmsNbmEstado)) {

            result = icmsNbmEstado.getIcnePercIcmsFecp();

        } else {

            List<IcmsEstados> icmsEstados =
                    icmsService.findIcmsEstadosByUfs(ufOrigem, Arrays.asList(ufDestino));

            if (icmsEstados != null && icmsEstados.size() == 1)
                result = icmsEstados.iterator().next().getIcmePercIcmsFecp();
        }

        if (result == null) {
            throw new TaxNotFoundException(MessageFormat.format(
                    "Não foi possível determinar o FECP Venda para a chave NCM, SEQ_NCM, UF_ORIGEM, UF_DESTINO, ID_CIA, DIA [{0}, {1}, {2}, {3}, {4}, {5}]",
                    ncm.toString(), ncmSeq, ufOrigem, ufDestino, TaxUtil.ID_CIA,
                    new Timestamp(System.currentTimeMillis())));
        }

        return result;
    }

    // TODO uncomment this code! Just a mock for integration test below.
    /*
     * public Map<Integer, Boolean> findRegimeEspecialFiliais(Integer idCia){ final Map<Integer,
     * Boolean> mapFilialRegimeEspecial = new HashMap<Integer, Boolean>();
     * List<FilialRegimeEspecial> filialRegimeEspecials =
     * filialRegimeEspecialRepository.findByIdCia(idCia); if(filialRegimeEspecials != null){
     * filialRegimeEspecials.forEach(filial -> mapFilialRegimeEspecial.put(filial.getIdFilial(),
     * filial.getFreeInRegime() != null ? filial.getFreeInRegime().equals("S") : false)); } return
     * mapFilialRegimeEspecial; }
     */

    // TODO delete this!
    public Map<Integer, Boolean> findRegimeEspecialFiliais(Integer idCia) {
        Map<Integer, Boolean> mapFilialRegimeEspecial = new HashMap<Integer, Boolean>();
        mapFilialRegimeEspecial.put(37, true);
        mapFilialRegimeEspecial.put(48, true);
        mapFilialRegimeEspecial.put(55, true);
        mapFilialRegimeEspecial.put(65, true);
        return mapFilialRegimeEspecial;
    }

    public List<Icms> findIcmsesPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            LinkedList<String> ufsDestino, List<String> sentidos) throws TaxException {
        List<Icms> icmsVendas = new ArrayList<Icms>();

        // TODO quando os itens forem migrados para Umbrella precisamos buscar o
        // imposto primeiro pelo item
        List<NbmEstado> nbmEstados = icmsService.findIcmsOnNbmEstadosByNbmAndUfs(ncm, ncmSeq,
                ufOrigem, ufsDestino, sentidos);

        for (NbmEstado nbmEstado : nbmEstados) {
            if (null != nbmEstado.getNbmePercIcms()) {
                Icms icmsVenda = new Icms(nbmEstado.getNbmeIdEstadoDe(),
                        nbmEstado.getNbmeIdEstadoPara(), nbmEstado.getNbmePercIcms());
                if (!nbmEstado.getNbmeIdEstadoDe().equals(nbmEstado.getNbmeIdEstadoPara())) {
                    icmsVenda.setTipoImposto(TipoImposto.INTERESTADUAL);
                }
                icmsVendas.add(icmsVenda);
                ufsDestino.remove(icmsVenda.getUfDestino());
            }
        }

        if (ufsDestino.size() > 0) {
            // busca registro da tabela ICNE (por NCM)
            List<IcmsNbmEstado> icmsNbmEstados = icmsService.findIcmsNbmEstadosFromIcne(ncm, ncmSeq,
                    TaxUtil.ID_CIA, ufOrigem, ufsDestino, sentidos);

            for (IcmsNbmEstado icmsNbmEstado : icmsNbmEstados) {
                if (TaxUtil.isAtivoAndHasIcms(icmsNbmEstado)) {
                    Icms icmsVenda = new Icms(icmsNbmEstado.getIcneIdEstadoDe(),
                            icmsNbmEstado.getIcneIdEstadoPara(), icmsNbmEstado.getIcnePercIcms());
                    if (!icmsNbmEstado.getIcneIdEstadoDe()
                            .equals(icmsNbmEstado.getIcneIdEstadoPara())) {
                        icmsVenda.setTipoImposto(TipoImposto.INTERESTADUAL);
                    }
                    icmsVendas.add(icmsVenda);
                    ufsDestino.remove(icmsVenda.getUfDestino());
                }
            }
        }

        if (ufsDestino.size() > 0) {
            // Não encontrou alguns ICMSs na ICNE ou eles estavam desativados
            // busca os registrados da tabela ICMS_ESTADOS
            List<IcmsEstados> icmsEstados = icmsService.findIcmsEstadosByUfs(ufOrigem, ufsDestino);

            for (IcmsEstados icmsEstado : icmsEstados) {
                Icms icmsVenda = new Icms(ufOrigem, icmsEstado.getIcmeIdEstadoPara(),
                        icmsEstado.getIcmePeIcms());
                if (!ufOrigem.equals(icmsEstado.getIcmeIdEstadoPara())) {
                    icmsVenda.setTipoImposto(TipoImposto.INTERESTADUAL);
                }
                icmsVendas.add(icmsVenda);
                ufsDestino.remove(icmsVenda.getUfDestino());
            }
        }

        if (ufsDestino.size() > 0) {
            logger.error(
                    "Não encontrou ICMS interestadual para todos os estados destino. Problema de parametrização.");
            throw new TaxNotFoundException(
                    "Não foi possível encontrar ICMS interestadual para todos estados solicitados. Verifique parametrização fiscal.");
        }

        return icmsVendas;
    }

    public List<Icms> findIcmsInternosPercent(Long ncm, Integer ncmSeq, List<String> sentidos)
            throws TaxException {
        List<Icms> icmsInternos = new ArrayList<Icms>();
        List<String> ufsParaBuscar = new ArrayList<String>(ufsBr);

        List<NbmEstado> nbmEstados = icmsService.findNbmEstadoInternas(ncm, ncmSeq, sentidos);
        // quando usa nbm recebe um array de nulls..
        for (NbmEstado nbmEstado : nbmEstados) {
            if (null != nbmEstado.getNbmePercIcms()) {
                Icms icmsInterno = new Icms(nbmEstado.getNbmeIdEstadoDe(),
                        nbmEstado.getNbmeIdEstadoPara(), nbmEstado.getNbmePercIcms());
                icmsInternos.add(icmsInterno);
                ufsParaBuscar.remove(nbmEstado.getNbmeIdEstadoDe());
            }
        }

        if (ufsParaBuscar.size() > 0) {
            // não encontrou todos os ICMSs na nbmEstados
            List<IcmsNbmEstado> icnes =
                    icmsService.findIcneInternas(ncm, ncmSeq, TaxUtil.ID_CIA, sentidos);
            for (IcmsNbmEstado icne : icnes) {
                if (TaxUtil.isAtivoAndHasIcms(icne)
                        && ufsParaBuscar.contains(icne.getIcneIdEstadoDe())) {
                    Icms icmsInterno = new Icms(icne.getIcneIdEstadoDe(),
                            icne.getIcneIdEstadoPara(), icne.getIcnePercIcms());
                    icmsInternos.add(icmsInterno);
                    ufsParaBuscar.remove(icne.getIcneIdEstadoDe());
                }
            }
        }

        if (ufsParaBuscar.size() > 0) {
            // não encontrou todos os ICMSs na icne
            List<IcmsEstados> icmsesEstados = icmsService.findIcmsEstadosInternas();
            for (IcmsEstados icmsEstados : icmsesEstados) {
                if (ufsParaBuscar.contains(icmsEstados.getIcmeIdEstadoDe())) {
                    Icms icmsInterno = new Icms(icmsEstados.getIcmeIdEstadoDe(),
                            icmsEstados.getIcmeIdEstadoPara(), icmsEstados.getIcmePeIcms());
                    icmsInternos.add(icmsInterno);
                    ufsParaBuscar.remove(icmsEstados.getIcmeIdEstadoDe());
                }
            }
        }

        if (ufsParaBuscar.size() > 0) {
            logger.error("Não encontrou ICMS interna para todos os estados NCM[" + ncm
                    + "]. Problema de parametrização.");
            throw new TaxNotFoundException(
                    "Não foi possível encontrar ICMS interna para todos estados solicitados. Verifique parametrização fiscal.");
        }

        return icmsInternos;
    }

    public List<BaseReduzida> findBaseReduzidasPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            LinkedList<String> ufsDestino, List<String> sentidos) {

        List<BaseReduzida> baseReduzidas = new ArrayList<BaseReduzida>();

        List<NbmEstado> nbmEstados = icmsService.findIcmsOnNbmEstadosByNbmAndUfs(ncm, ncmSeq,
                ufOrigem, ufsDestino, sentidos);

        for (NbmEstado nbmEstado : nbmEstados) {
            if (null != nbmEstado.getNbmePercRedIcms()) {
                BaseReduzida baseReduzida = new BaseReduzida(nbmEstado.getNbmeIdEstadoDe(),
                        nbmEstado.getNbmeIdEstadoPara(),
                        TaxUtil.subtractWithNull(new Float(100), nbmEstado.getNbmePercRedIcms()));
                if (!nbmEstado.getNbmeIdEstadoDe().equals(nbmEstado.getNbmeIdEstadoPara())) {
                    baseReduzida.setTipoImposto(TipoImposto.INTERESTADUAL);
                }
                baseReduzidas.add(baseReduzida);
                ufsDestino.remove(baseReduzida.getUfDestino());
            }
        }

        // busca registro da tabela ICNE
        if (ufsDestino.size() > 0) {

            List<IcmsNbmEstado> icmsNbmEstados = icmsService.findIcmsNbmEstadosFromIcne(ncm, ncmSeq,
                    TaxUtil.ID_CIA, ufOrigem, ufsDestino, sentidos);

            for (IcmsNbmEstado icmsNbmEstado : icmsNbmEstados) {
                if (TaxUtil.isAtivoAndHasRedBase(icmsNbmEstado)) {
                    BaseReduzida baseReduzida = new BaseReduzida(icmsNbmEstado.getIcneIdEstadoDe(),
                            icmsNbmEstado.getIcneIdEstadoPara(),
                            (100 - icmsNbmEstado.getIcnePercRedBase()));
                    if (!icmsNbmEstado.getIcneIdEstadoDe()
                            .equals(icmsNbmEstado.getIcneIdEstadoPara())) {
                        baseReduzida.setTipoImposto(TipoImposto.INTERESTADUAL);
                    }
                    baseReduzidas.add(baseReduzida);
                    ufsDestino.remove(baseReduzida.getUfDestino());
                }
            }
        }

        if (ufsDestino.size() > 0) {
            // Não encontrou algumas bases na ICNE ou elas estavam desativadas
            // neste caso considera base cheia (100)
            for (String ufDestino : ufsDestino) {
                BaseReduzida baseReduzida = new BaseReduzida(ufOrigem, ufDestino, new Float(100));
                if (!ufOrigem.equals(ufDestino)) {
                    baseReduzida.setTipoImposto(TipoImposto.INTERESTADUAL);
                }
                baseReduzidas.add(baseReduzida);
            }
        }

        return baseReduzidas;
    }

    public List<BaseReduzida> findBaseReduzidaInternoPercent(Long ncm, Integer ncmSeq,
            List<String> sentidos) throws TaxException {
        List<BaseReduzida> baseReduzidasInternas = new ArrayList<BaseReduzida>();
        List<String> ufsParaBuscar = new ArrayList<String>(ufsBr);

        List<NbmEstado> nbmEstados = icmsService.findNbmEstadoInternas(ncm, ncmSeq, sentidos);
        for (NbmEstado nbmEstado : nbmEstados) {
            if (null != nbmEstado.getNbmePercRedIcms()) {
                BaseReduzida brInterna = new BaseReduzida(nbmEstado.getNbmeIdEstadoDe(),
                        nbmEstado.getNbmeIdEstadoPara(),
                        TaxUtil.subtractWithNull(new Float(100), nbmEstado.getNbmePercRedIcms()));
                baseReduzidasInternas.add(brInterna);
                ufsParaBuscar.remove(nbmEstado.getNbmeIdEstadoDe());
            }
        }

        if (ufsParaBuscar.size() > 0) {
            // não encontrou todos os ICMSs na nbmEstados
            List<IcmsNbmEstado> icnes =
                    icmsService.findIcneInternas(ncm, ncmSeq, TaxUtil.ID_CIA, sentidos);
            for (IcmsNbmEstado icne : icnes) {
                if (TaxUtil.isAtivoAndHasRedBase(icne)
                        && ufsParaBuscar.contains(icne.getIcneIdEstadoDe())) {
                    BaseReduzida brInterna = new BaseReduzida(icne.getIcneIdEstadoDe(),
                            icne.getIcneIdEstadoPara(),
                            TaxUtil.subtractWithNull(new Float(100), icne.getIcnePercRedBase()));
                    baseReduzidasInternas.add(brInterna);
                    ufsParaBuscar.remove(icne.getIcneIdEstadoDe());
                }
            }
        }

        if (ufsParaBuscar.size() > 0) {
            // Não encontrou algumas bases na ICNE ou elas estavam desativadas
            // neste caso considera base cheia (100)
            Iterator<String> itUfs = ufsParaBuscar.iterator();
            while (itUfs.hasNext()) {
                String uf = itUfs.next();
                BaseReduzida baseReduzida = new BaseReduzida(uf, uf, new Float(100));
                baseReduzidasInternas.add(baseReduzida);
                itUfs.remove();
            }
        }

        if (ufsParaBuscar.size() > 0) {
            logger.error("Não encontrou BASE REDUZIDAS interna para todos os estados NCM[" + ncm
                    + "]. Problema de parametrização.");
            throw new TaxNotFoundException(
                    "Não foi possível encontrar BASE REDUZIDAS interna para todos estados solicitados. Verifique parametrização fiscal.");
        }

        return baseReduzidasInternas;
    }

    /**
     *
     * Método calcula o ICMSST ou DIFAL, de acordo com a regra cadastrada.
     *
     * @param ncm
     * @param ncmSeq
     * @param valorBase
     * @param ufOrigem
     * @param ufDestino
     * @param sentidos
     * @param nationalSimple
     * @return
     * @throws TaxException
     */
    public Icms calculateICMSSubstituicaoTributaria(Long ncm, Integer ncmSeq, Float valorBase,
            String ufOrigem, String ufDestino, List<String> sentidos, boolean nationalSimple,
            String tipoConsumo, boolean ignoreDifal) throws TaxException {
        Float result = 0f;
        Float ivaPercent;

        Float icmsPercent = findIcmsPercent(ncm, ncmSeq, ufOrigem, ufDestino, sentidos);

        if (icmsPercent == null) {
            logger.info("Não foi encontrada alíquota para ICMS, para este ncm, origem e destino ("
                    + ncm.toString() + ", " + ufOrigem + ", " + ufDestino);

            return null;
        }

        if (valorBase == null) {
            logger.info("Não é possível calcular ICMS ST sem valor base informado.");
            return null;
        }

        Float redBasePercent = findBaseReduzidaPercent(ncm, ncmSeq, ufOrigem, ufDestino, sentidos);
        if (null != redBasePercent && redBasePercent > 0f) {
            icmsPercent = (redBasePercent * icmsPercent) / 100f;
        }
        Float icmsValue = valorBase * (icmsPercent / 100f);

        NbmEstado nbmEstado =
                nbmEstadoService.findNbmEstado(ncm, ncmSeq, ufOrigem, ufDestino, sentidos);

        ivaPercent = getImpostoValorAgregadoPercent(ncm, ncmSeq, ufOrigem, ufDestino, sentidos,
                nationalSimple, nbmEstado);

        Float baseIcmsSubstituicaoTributariaNred = valorBase * (1 + (ivaPercent / 100));

        String inAplicacao = nbmEstado.getNbmeInAplicacao();
        Float percAplicacao = nbmEstado.getNbmePercAplicacao();
        Float margemLucroValue = valorBase * (percAplicacao / 100);

        Float baseIcmsSubstituicaoTributaria;
        if (null != inAplicacao) {
            if (inAplicacao.equalsIgnoreCase("A")) {
                baseIcmsSubstituicaoTributaria =
                        baseIcmsSubstituicaoTributariaNred + margemLucroValue;
            } else if (inAplicacao.equalsIgnoreCase("R")) {
                baseIcmsSubstituicaoTributaria =
                        baseIcmsSubstituicaoTributariaNred - margemLucroValue;
            } else {
                throw new TaxNotFoundException(
                        "Não foi possível calcular o ICMS ST. O valor de IN_APLICACAO é inválido.");
            }
        } else {
            throw new TaxNotFoundException(
                    "Não foi possível calcular o ICMS ST. O valor de IN_APLICACAO é nulo.");
        }

        Float icmsSubstituicaoTributariaPercent = nbmEstado.getNbmePercSubstrib();
        Icms interstateIcms = new Icms();

        if (!ignoreDifal && isDifal(nbmEstado, tipoConsumo, ufOrigem, ufDestino)) {
            result = calculateDIFAL(valorBase, icmsSubstituicaoTributariaPercent, icmsPercent);
            icmsPercent = icmsSubstituicaoTributariaPercent - icmsPercent;
            interstateIcms.setValorBase(valorBase);
        } else if (ignoreDifal || isICMSST(nbmEstado, tipoConsumo, ufOrigem, ufDestino)) {
            result = calculateICMSST(baseIcmsSubstituicaoTributaria,
                    icmsSubstituicaoTributariaPercent, icmsValue);
            interstateIcms.setValorBase(baseIcmsSubstituicaoTributaria);
        } else {
            throw new TaxNotFoundException(
                    "Não foram achados impostos interestaduais com o tipo de consumo :"
                            + tipoConsumo,
                    ncm.toString(), ufOrigem, ufDestino);
        }

        if (result < 0f) {
            logger.warn("Atenção! O cálculo de ICMS ST do NCM " + ncm
                    + " resultou negativo. O valor foi transformado para 0.");
            result = 0f;
        }

        interstateIcms.setUfOrigem(ufOrigem);
        interstateIcms.setUfDestino(ufDestino);
        interstateIcms.setIcmsPercent(icmsPercent);
        interstateIcms.setIcms(result);

        return interstateIcms;
    }

    private Float calculateICMSST(Float baseIcmsSubstituicaoTributaria, Float icmsInterstatePercent,
            Float icmsValue) {
        return (baseIcmsSubstituicaoTributaria * (icmsInterstatePercent / 100)) - icmsValue;
    }

    private Float calculateDIFAL(Float baseValue, Float icmsInterstatePercent, Float icmsPercent) {
        return baseValue * (icmsInterstatePercent - icmsPercent) / 100;
    }

    private boolean isDifal(NbmEstado nbmEstado, String tipoConsumo, String ufOrigem,
            String ufDestino) {
        return tipoConsumo != null && nbmEstado != null && nbmEstado.getNbmeInConvenio() != null
                && nbmEstado.getNbmeInDifalSt() != null && tipoConsumo.equalsIgnoreCase("OWN")
                && nbmEstado.getNbmeInConvenio().equals("S")
                && nbmEstado.getNbmeInDifalSt().equals("S") && !ufOrigem.equals(ufDestino);
    }

    private boolean isICMSST(NbmEstado nbmEstado, String tipoConsumo, String ufOrigem,
            String ufDestino) {
        return tipoConsumo != null && nbmEstado != null && nbmEstado.getNbmeInConvenio() != null
                && nbmEstado.getNbmeInDifalSt() != null && tipoConsumo.equalsIgnoreCase("RESALE")
                && nbmEstado.getNbmeInConvenio().equals("S")
                && nbmEstado.getNbmeInDifalSt().equals("N");

    }

    public Float findBaseReduzidaPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino, List<String> sentidos) {
        Float result = null;

        result = icmsService.findBaseReduzidaByNbmAndUf(ncm, ncmSeq, ufOrigem, ufDestino, sentidos);

        // TODO existem registros duplicados por {nbm, seq_nbm, uforigem,
        // ufdestino, idCia} nesta tabela, qual utilizar?
        if (result == null) {
            IcmsNbmEstado icmsNbmEstado = icmsService.findIcmsNbmEstadoFromIcne(ncm, ncmSeq,
                    TaxUtil.ID_CIA, ufOrigem, ufDestino, sentidos);

            if (TaxUtil.isAtivoAndHasRedBase(icmsNbmEstado)) {
                result = icmsNbmEstado.getIcnePercRedBase();
            }
        }

        return (result == null ? new Float(100) : (100 - result));
    }

    /**
     * IVA - Imposto de Valor Agregado
     */
    public Float getImpostoValorAgregadoPercent(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino, List<String> sentidos, boolean nationalSimple, NbmEstado nbmEstado)
            throws TaxException {

        if (nationalSimple) {
            nbmEstado = nbmEstadoService.findNbmEstado(ncm, ncmSeq, ufDestino, ufDestino, sentidos);
        }

        return null != nbmEstado ? nbmEstado.getNbmePercMargemLucro() : 0f;
    }

    public boolean checkHasIcmsSubstituicaoTributaria(Long ncm, Integer ncmSeq, String ufOrigem,
            String ufDestino, List<String> sentidos) throws TaxException {

        try {

            NbmEstado nbmEstado =
                    nbmEstadoService.findNbmEstado(ncm, ncmSeq, ufOrigem, ufDestino, sentidos);
            return (nbmEstado != null);

        } catch (TaxServiceException | TaxNotFoundException tex) {
            logger.debug(tex.getMessage());
            return false;
        }
    }

    /**
     *
     * PPb - processo produtivo basico
     *
     * {@link http://www.liraa.com.br/conteudo/2562/processo-produtivo-basico-ppb}
     */
    public boolean checkHasPpb(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
            List<String> sentidos) {
        IcmsNbmEstado icne = icmsService.findIcmsNbmEstadoFromIcne(ncm, ncmSeq, TaxUtil.ID_CIA,
                ufOrigem, ufDestino, sentidos);

        return (icne != null && icne.getIcneInForcaBaseReduzida() != null
                && icne.getIcneInForcaBaseReduzida().equalsIgnoreCase("S"));
    }

}
