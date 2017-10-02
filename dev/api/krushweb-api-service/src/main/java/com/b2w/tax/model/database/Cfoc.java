package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DS_CFOC", schema="USU_PRICE")
public class Cfoc {

	@Id
	@Column(name="CFOC_ID_NATOPE")
	private Integer cfocIdNatope;
	
	@Column(name="CFOC_SEQ")
	private Integer cfocSeq;
	
	@Column(name="CFOC_NOME")
	private String cfocNome;
	
	@Column(name="CFOC_ID_AGRUDIPI")
	private Integer cfocIdAgrudipi;
	
	@Column(name="CFOC_TP_ICMSC")
	private String cfocTpIcmsc;
	
	@Column(name="CFOC_TP_IPIC")
	private String cfocTpIpic;
	
	@Column(name="CFOC_PERC_ICMS")
	private Integer cfocPercIcms;
	
	@Column(name="CFOC_IN_ST")
	private String cfocInSt;
	
	@Column(name="CFOC_IN_ALTPED")
	private String cfocInAltped;
	
	@Column(name="CFOC_IN_CONTABILIZA")
	private String cfocInContabiliza;
	
	@Column(name="CFOC_IN_GERATIT")
	private String cfocInGeratit;
	
	@Column(name="CFOC_IN_CP_CR")
	private String cfocInCpCr;
	
	@Column(name="CFOC_ID_DOCTO_CP_CR")
	private String cfocIdDoctoCpCr;
	
	@Column(name="CFOC_IN_ATUEST")
	private String cfocInAtuest;
	
	@Column(name="CFOC_ID_LOGEST")
	private Integer cfocIdLogest;
	
	@Column(name="CFOC_IN_GERANF")
	private String cfocInGeranf;
	
	@Column(name="CFOC_ID_TIPOPE")
	private String cfocIdTipope;
	
	@Column(name="CFOC_IN_IRRF")
	private String cfocInIrrf;
	
	@Column(name="CFOC_IN_INSS")
	private String cfocInInss;
	
	@Column(name="CFOC_IN_ISS")
	private String cfocInIss;
	
	@Column(name="CFOC_IN_GERARF")
	private String cfocInGerarf;
	
	@Column(name="CFOC_IN_BASE_DIGITADA")
	private String cfocInBaseDigitada;
	
	@Column(name="CFOC_IN_BASE_FIN")
	private String cfocInBaseFin;
	
	@Column(name="CFOC_IN_BASE_PIS")
	private String cfocInBasePis;
	
	@Column(name="CFOC_IN_IPI_BASE_ICMS")
	private String cfocInIpiBaseIcms;
	
	@Column(name="CFOC_SITUACAO")
	private String cfocSituacao;
	
	@Column(name="CFOC_OBJETIVO")
	private String cfocObjetivo;
	
	@Column(name="CFOC_USUARIO")
	private String cfocUsuario;
	
	@Column(name="CFOC_DT_ALTERACAO")
	private Timestamp cfocDtAlteracao;
	
	@Column(name="CFOC_HR_ALTERACAO")
	private String cfocHrAlteracao;
	
	@Column(name="CFOC_PERC_RED_ICMS")
	private Float cfocPercRedIcms;
	
	@Column(name="CFOC_ALIQ_IRRF")
	private Float cfocAliqIrrf;
	
	@Column(name="CFOC_ID_MODALIDADE")
	private Integer cfocIdModalidade;
	
	@Column(name="CFOC_IN_PRECOMEDIO")
	private String cfocInPrecomedio;
	
	@Column(name="CFOC_IN_BASE_CSLL")
	private String cfocInBaseCsll;
	
	@Column(name="CFOC_IN_VALOR_REFERIDO")
	private String cfocInValorReferido;
	
	@Column(name="CFOC_NOME_CONSIGNACAO")
	private String cfocNomeConsignacao;
	
	@Column(name="CFOC_NOME_MISTURA")
	private String cfocNomeMistura;
	
	@Column(name="CFOC_IN_IPI_ST")
	private String cfocInIpiSt;
	
	@Column(name="CFOC_IN_ICMS_ST")
	private String cfocInIcmsSt;
	
	@Column(name="CFOC_CST_ICMS")
	private Integer cfocCstIcms;
	
	@Column(name="CFOC_CST_PIS")
	private Integer cfocCstPis;
	
	@Column(name="CFOC_CST_COFINS")
	private Integer cfocCstCofins;
	
	@Column(name="CFOC_CST_IPI")
	private String cfocCstIpi;
	
	@Column(name="CFOC_ID_CIA")
	private Integer cfocIdCia;
	
	@Column(name="CFOC_IN_PRECO_MEDIO_ICMS")
	private String cfocInPrecoMedioIcms;
	
	@Column(name="CFOC_ID_FINALIDADE")
	private Integer cfocIdFinalidade;
	
	@Column(name="CFOC_IN_GERA_PC")
	private String cfocInGeraPc;
	
	public Integer getCfocIdNatope() {
		return cfocIdNatope;
	}

	public void setCfocIdNatope(Integer cfocIdNatope) {
		this.cfocIdNatope= cfocIdNatope;
	}
	
	public Integer getCfocSeq() {
		return cfocIdNatope;
	}

	public void setCfocSeq(Integer cfocSeq) {
		this.cfocSeq = cfocSeq;
	}
	
	public String getCfocNome() {
		return cfocNome;
	}

	public void setCfocNome(String cfocNome) {
		this.cfocNome = cfocNome;
	}
	
	public Integer getCfocIdAgrudipi() {
		return cfocIdAgrudipi;
	}

	public void setCfocIdAgrudipi(Integer cfocIdAgrudipi) {
		this.cfocIdAgrudipi = cfocIdAgrudipi;
	}
	
	public String getCfocTpIcmsc() {
		return cfocTpIcmsc;
	}

	public void setCfocTpIcmsc(String cfocTpIcmsc) {
		this.cfocTpIcmsc = cfocTpIcmsc;
	}
	
	public String getCfocTpIpic() {
		return cfocTpIpic;
	}

	public void setCfocTpIpic(String cfocTpIpic) {
		this.cfocTpIpic = cfocTpIpic;
	}
	
	public Integer getCfocPercIcms() {
		return cfocPercIcms;
	}

	public void setCfocPercIcms(Integer cfocPercIcms) {
		this.cfocPercIcms = cfocPercIcms;
	}
	
	public String getCfocInSt() {
		return cfocInSt;
	}

	public void setCfocInSt(String cfocInSt) {
		this.cfocInSt = cfocInSt;
	}
	
	public String getCfocInAltped() {
		return cfocInAltped;
	}

	public void setCfocInAltped(String cfocInAltped) {
		this.cfocInAltped = cfocInAltped;
	}
	
	public String getCfocInContabiliza() {
		return cfocInContabiliza;
	}

	public void setCfocInContabiliza(String cfocInContabiliza) {
		this.cfocInContabiliza = cfocInContabiliza;
	}
	
	public String getCfocInGeratit() {
		return cfocInGeratit;
	}

	public void setCfocInGeratit(String cfocInGeratit) {
		this.cfocInGeratit = cfocInGeratit;
	}
	
	public String getCfocInCpCr() {
		return cfocInCpCr;
	}

	public void setCfocInCpCr(String cfocInCpCr) {
		this.cfocInCpCr = cfocInCpCr;
	}
	
	public String getCfocIdDoctoCpCr() {
		return cfocIdDoctoCpCr;
	}

	public void setCfocIdDoctoCpCr(String cfocIdDoctoCpCr) {
		this.cfocIdDoctoCpCr = cfocIdDoctoCpCr;
	}

	public String getCfocInAtuest() {
		return cfocInAtuest;
	}

	public void setCfocInAtuest(String cfocInAtuest) {
		this.cfocInAtuest = cfocInAtuest;
	}
	
	public Integer getCfocIdLogest() {
		return cfocIdLogest;
	}

	public void setCfocIdLogest(Integer cfocIdLogest) {
		this.cfocIdLogest = cfocIdLogest;
	}
	
	public String getCfocInGeranf() {
		return cfocInGeranf;
	}

	public void setCfocInGeranf(String cfocInGeranf) {
		this.cfocInGeranf = cfocInGeranf;
	}
	
	public String getCfocIdTipope() {
		return cfocIdTipope;
	}

	public void setCfocIdTipope(String cfocIdTipope) {
		this.cfocIdTipope = cfocIdTipope;
	}
	
	public String getCfocInIrrf() {
		return cfocInIrrf;
	}

	public void setCfocInIrrf(String cfocInIrrf) {
		this.cfocInIrrf = cfocInIrrf;
	}
	
	public String getCfocInInss() {
		return cfocInInss;
	}

	public void setCfocInInss(String cfocInInss) {
		this.cfocInInss = cfocInInss;
	}
	
	public String getCfocInIss() {
		return cfocInIss;
	}

	public void setCfocInIss(String cfocInIss) {
		this.cfocInIss = cfocInIss;
	}
	
	public String getCfocInGerarf() {
		return cfocInGerarf;
	}

	public void setCfocInGerarf(String cfocInGerarf) {
		this.cfocInGerarf  = cfocInGerarf;
	}
	
	public String getCfocInBaseDigitada() {
		return cfocInBaseDigitada;
	}

	public void setCfocInBaseDigitada(String cfocInBaseDigitada) {
		this.cfocInBaseDigitada = cfocInBaseDigitada;
	}
	
	public String getCfocInBaseFin() {
		return cfocInBaseFin;
	}

	public void setCfocInBaseFin(String cfocInBaseFin) {
		this.cfocInBaseFin = cfocInBaseFin;
	}
	
	public String getCfocInBasePis() {
		return cfocInBasePis;
	}

	public void setCfocInBasePis(String cfocInBasePis) {
		this.cfocInBasePis = cfocInBasePis;
	}
	
	public String getCfocInIpiBaseIcms() {
		return cfocInIpiBaseIcms;
	}

	public void setCfocInIpiBaseIcms(String cfocInIpiBaseIcms) {
		this.cfocInIpiBaseIcms = cfocInIpiBaseIcms;
	}
	
	public String getCfocSituacao() {
		return cfocSituacao;
	}

	public void setCfocSituacao(String cfocSituacao) {
		this.cfocSituacao = cfocSituacao;
	}
	
	public String getCfocObjetivo() {
		return cfocObjetivo;
	}

	public void setCfocObjetivo(String cfocObjetivo) {
		this.cfocObjetivo = cfocObjetivo;
	}
	
	public String getCfocUsuario() {
		return cfocUsuario;
	}

	public void setCfocUsuario(String cfocUsuario) {
		this.cfocUsuario = cfocUsuario;
	}
	
	public Timestamp getCfocDtAlteracao() {
		return cfocDtAlteracao;
	}

	public void setCfocDtAlteracao(Timestamp cfocDtAlteracao) {
		this.cfocDtAlteracao = cfocDtAlteracao;
	}
	
	public String getCfocHrAlteracao() {
		return cfocHrAlteracao;
	}

	public void setCfocHrAlteracao(String cfocHrAlteracao) {
		this.cfocHrAlteracao = cfocHrAlteracao;
	}
	
	public Float getCfocPercRedIcms() {
		return cfocPercRedIcms;
	}

	public void setCfocPercRedIcms(Float cfocPercRedIcms) {
		this.cfocPercRedIcms = cfocPercRedIcms;
	}
	
	public Float getCfocAliqIrrf() {
		return cfocAliqIrrf;
	}

	public void setCfocAliqIrrf(Float cfocAliqIrrf) {
		this.cfocAliqIrrf = cfocAliqIrrf;
	}
	
	public Integer getCfocIdModalidade() {
		return cfocIdModalidade;
	}

	public void setCfocIdModalidade(Integer cfocIdModalidade) {
		this.cfocIdModalidade = cfocIdModalidade;
	}
	
	public String getCfocInPrecomedio() {
		return cfocInPrecomedio;
	}

	public void setCfocInPrecomedio(String cfocInPrecomedio) {
		this.cfocInPrecomedio = cfocInPrecomedio;
	}
	
	public String getCfocInBaseCsll() {
		return cfocInBaseCsll;
	}

	public void setCfocInBaseCsll(String cfocInBaseCsll) {
		this.cfocInBaseCsll = cfocInBaseCsll;
	}
	
	public String getCfocInValorReferido() {
		return cfocInValorReferido;
	}

	public void setCfocInValorReferido(String cfocInValorReferido) {
		this.cfocInValorReferido = cfocInValorReferido;
	}
	
	public String getCfocNomeConsignacao() {
		return cfocNomeConsignacao;
	}

	public void setCfocNomeConsignacao(String cfocNomeConsignacao) {
		this.cfocNomeConsignacao = cfocNomeConsignacao;
	}
	
	public String getCfocNomeMistura() {
		return cfocNomeMistura;
	}

	public void setCfocNomeMistura(String cfocNomeMistura) {
		this.cfocNomeMistura = cfocNomeMistura;
	}
	
	public String getCfocInIpiSt() {
		return cfocInIpiSt;
	}

	public void setCfocInIpiSt(String cfocInIpiSt) {
		this.cfocInIpiSt = cfocInIpiSt;
	}
	
	public String getCfocInIcmsSt() {
		return cfocInIcmsSt;
	}

	public void setCfocInIcmsSt(String cfocInIcmsSt) {
		this.cfocInIcmsSt = cfocInIcmsSt;
	}
	
	public Integer getCfocCstIcms() {
		return cfocCstIcms;
	}

	public void setCfocCstIcms(Integer cfocCstIcms) {
		this.cfocCstIcms = cfocCstIcms;
	}
	
	public Integer getCfocCstPis() {
		return cfocCstPis;
	}

	public void setCfocCstPis(Integer cfocCstPis) {
		this.cfocCstPis = cfocCstPis;
	}
	
	public Integer getCfocCstCofins() {
		return cfocCstCofins;
	}

	public void setCfocCstCofins(Integer cfocCstCofins) {
		this.cfocCstCofins = cfocCstCofins;
	}
	
	public String getCfocCstIpi() {
		return cfocCstIpi;
	}

	public void setCfocCstIpi(String cfocCstIpi) {
		this.cfocCstIpi = cfocCstIpi;
	}
	
	public Integer getCfocIdCia() {
		return cfocIdCia;
	}

	public void setCfocIdCia(Integer cfocIdCia) {
		this.cfocIdCia = cfocIdCia;
	}
	
	public String getCfocInPrecoMedioIcms() {
		return cfocInPrecoMedioIcms;
	}

	public void setCfocInPrecoMedioIcms(String cfocInPrecoMedioIcms) {
		this.cfocInPrecoMedioIcms = cfocInPrecoMedioIcms;
	}
	
	public Integer getCfocIdFinalidade() {
		return cfocIdFinalidade;
	}

	public void setCfocIdFinalidade(Integer cfocIdFinalidade) {
		this.cfocIdFinalidade = cfocIdFinalidade;
	}
	
	public String getCfocInGeraPc() {
		return cfocInGeraPc;
	}

	public void setCfocInGeraPc(String cfocInGeraPc) {
		this.cfocInGeraPc = cfocInGeraPc;
	}
	
}
