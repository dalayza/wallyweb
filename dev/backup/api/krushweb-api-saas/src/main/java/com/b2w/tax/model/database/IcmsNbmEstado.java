package com.b2w.tax.model.database;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "DS_ICMS_NBM_ESTADO", schema = "USU_PRICE")
@IdClass(NbmSeqIcne.class)
@Cacheable("icmsNbmEstadoFromIcneCache")
public class IcmsNbmEstado implements Serializable {

	private static final long serialVersionUID = -3917440224475341643L;

	@Id
	@Column(name = "ICNE_ID_NBM")
	private Long icneIdNbm;

	@Id
	@Column(name = "ICNE_ID_CIA")
	private Integer icneIdCia;

	@Id
	@Column(name = "ICNE_ID_ESTADO_DE")
	private String icneIdEstadoDe;

	@Id
	@Column(name = "ICNE_ID_ESTADO_PARA")
	private String icneIdEstadoPara;

	@Column(name = "ICNE_SEQ_NBM")
	private Integer icneSeqNbm;

	@Column(name = "ICNE_PERC_ICMS")
	private Float icnePercIcms;

	@Column(name = "ICNE_PERC_RED_BASE")
	private Float icnePercRedBase;

	@Column(name = "ICNE_SITUACAO")
	private String icneSituacao;

	@Column(name = "ICNE_USUARIO")
	private String icneUsuario;

	@Column(name = "ICNE_DATAHORA")
	private Timestamp icneDatahora;

	@Column(name = "ICNE_SEQ")
	private Integer icneSeq;

	@Column(name = "ICNE_VIGENCIA_INI")
	private Timestamp icneVigenciaIni;

	@Column(name = "ICNE_VIGENCIA_FIM")
	private Timestamp icneVigenciaFim;

	@Column(name = "ICNE_SENTIDO")
	private String icneSentido;

	@Column(name = "ICNE_IN_FORCA_BASE_REDUZIDA")
	private String icneInForcaBaseReduzida;

	@Column(name = "ICNE_PERC_ICMS_FECP")
	private Float icnePercIcmsFecp;
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "ICNE_ID_NBM", referencedColumnName = "nbmIdNbm", insertable = false, updatable = false),
			@JoinColumn(name = "ICNE_SEQ_NBM", referencedColumnName = "nbmSeq", insertable = false, updatable = false),
			@JoinColumn(name = "ICNE_ID_CIA", referencedColumnName = "nbmIdCia", insertable = false, updatable = false) })
	private Nbm nbm;

	public Integer getIcneIdCia() {
		return icneIdCia;
	}

	public void setIcneIdCia(Integer icneIdCia) {
		this.icneIdCia = icneIdCia;
	}

	public Long getIcneIdNbm() {
		return icneIdNbm;
	}

	public void setIcneIdNbm(Long icneIdNbm) {
		this.icneIdNbm = icneIdNbm;
	}

	public Integer getIcneSeqNbm() {
		return icneSeqNbm;
	}

	public void setIcneSeqNbm(Integer icneSeqNbm) {
		this.icneSeqNbm = icneSeqNbm;
	}

	public String getIcneIdEstadoDe() {
		return icneIdEstadoDe;
	}

	public void setIcneIdEstadoDe(String icneIdEstadoDe) {
		this.icneIdEstadoDe = icneIdEstadoDe;
	}

	public String getIcneIdEstadoPara() {
		return icneIdEstadoPara;
	}

	public void setIcneIdEstadoPara(String icneIdEstadoPara) {
		this.icneIdEstadoPara = icneIdEstadoPara;
	}

	public Float getIcnePercIcms() {
		return icnePercIcms;
	}

	public void setIcnePercIcms(Float icnePercIcms) {
		this.icnePercIcms = icnePercIcms;
	}

	public Float getIcnePercRedBase() {
		return icnePercRedBase;
	}

	public void setIcnePercRedBase(Float icnePercRedBase) {
		this.icnePercRedBase = icnePercRedBase;
	}

	public String getIcneSituacao() {
		return icneSituacao;
	}

	public void setIcneSituacao(String icneSituacao) {
		this.icneSituacao = icneSituacao;
	}

	public String getIcneUsuario() {
		return icneUsuario;
	}

	public void setIcneUsuario(String icneUsuario) {
		this.icneUsuario = icneUsuario;
	}

	public Timestamp getIcneDatahora() {
		return icneDatahora;
	}

	public void setIcneDatahora(Timestamp icneDatahora) {
		this.icneDatahora = icneDatahora;
	}

	public Integer getIcneSeq() {
		return icneSeq;
	}

	public void setIcneSeq(Integer icneSeq) {
		this.icneSeq = icneSeq;
	}

	public Timestamp getIcneVigenciaIni() {
		return icneVigenciaIni;
	}

	public void setIcneVigenciaIni(Timestamp icneVigenciaIni) {
		this.icneVigenciaIni = icneVigenciaIni;
	}

	public Timestamp getIcneVigenciaFim() {
		return icneVigenciaFim;
	}

	public void setIcneVigenciaFim(Timestamp icneVigenciaFim) {
		this.icneVigenciaFim = icneVigenciaFim;
	}

	public String getIcneSentido() {
		return icneSentido;
	}

	public void setIcneSentido(String icneSentido) {
		this.icneSentido = icneSentido;
	}

	public String getIcneInForcaBaseReduzida() {
		return icneInForcaBaseReduzida;
	}

	public void setIcneInForcaBaseReduzida(String icneInForcaBaseReduzida) {
		this.icneInForcaBaseReduzida = icneInForcaBaseReduzida;
	}

	public Float getIcnePercIcmsFecp() {
		return icnePercIcmsFecp;
	}

	public void setIcnePercIcmsFecp(Float icnePercIcmsFecp) {
		this.icnePercIcmsFecp = icnePercIcmsFecp;
	}

	public Nbm getNbm() {
		return nbm;
	}

	public void setNbm(Nbm nbm) {
		this.nbm = nbm;
	}

}