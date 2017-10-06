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
@Table(name="DS_NBM_ESTADO", schema="USU_PRICE")
@IdClass(NbmEstadoId.class)
@Cacheable("taxes")
public class NbmEstado implements Serializable{
	
	@Id
	private String nbmeIdEstadoPara;
	
	private Integer nbmeSeq;
	
	@Id
	@Column(name="NBME_ID_NBM")
	private Long nbmeIdNbm;
	
	@Id
	@Column(name="NBME_SEQ_NBM")
	private Integer nbmeSeqNbm;
	
	private String nbmeInAplicacao;
	
	private Float nbmePercAplicacao;
	
	private Timestamp nbmeDtVigencia;
	
	private Timestamp nbmeDtVigenciaFinal;
	
	private Float nbmePercSubstrib;
	
	private Float nbmePercMargemLucro;
	
	private Float nbmePercIcms;
	
	private Float nbmeBaseIcms;
	
	private Float nbmePercRedIcms;
	
	private String nbmeUsuario;
	
	private Timestamp nbmeDatahora;
	
	private String nbmeInConvenio;

	private String nbmeInDifalSt;
	
	@Column(name="NBME_SENTIDO")
	private String nbmeSentido;
	
	@Id
	private String nbmeIdEstadoDe;
	
	@Id
	@Column(name="NBME_ID_CIA")
	private Integer nbmeIdCia;
	
	@ManyToOne
	@JoinColumns(value={@JoinColumn(name="NBME_ID_NBM", referencedColumnName="nbmIdNbm", insertable=false, updatable=false), 
			@JoinColumn(name="NBME_SEQ_NBM", referencedColumnName="nbmSeq", insertable=false, updatable=false),
			@JoinColumn(name="NBME_ID_CIA", referencedColumnName="nbmIdCia", insertable=false, updatable=false)})
	private Nbm nbm;

	public String getNbmeIdEstadoPara() {
		return nbmeIdEstadoPara;
	}

	public void setNbmeIdEstadoPara(String nbmeIdEstadoPara) {
		this.nbmeIdEstadoPara = nbmeIdEstadoPara;
	}

	public Integer getNbmeSeq() {
		return nbmeSeq;
	}

	public void setNbmeSeq(Integer nbmeSeq) {
		this.nbmeSeq = nbmeSeq;
	}

	public Long getNbmeIdNbm() {
		return nbmeIdNbm;
	}

	public void setNbmeIdNbm(Long nbmeIdNbm) {
		this.nbmeIdNbm = nbmeIdNbm;
	}

	public Integer getNbmeSeqNbm() {
		return nbmeSeqNbm;
	}

	public void setNbmeSeqNbm(Integer nbmeSeqNbm) {
		this.nbmeSeqNbm = nbmeSeqNbm;
	}

	public String getNbmeInAplicacao() {
		return nbmeInAplicacao;
	}

	public void setNbmeInAplicacao(String nbmeInAplicacao) {
		this.nbmeInAplicacao = nbmeInAplicacao;
	}

	public Float getNbmePercAplicacao() {
		return nbmePercAplicacao;
	}

	public void setNbmePercAplicacao(Float nbmePercAplicacao) {
		this.nbmePercAplicacao = nbmePercAplicacao;
	}

	public Timestamp getNbmeDtVigencia() {
		return nbmeDtVigencia;
	}

	public void setNbmeDtVigencia(Timestamp nbmeDtVigencia) {
		this.nbmeDtVigencia = nbmeDtVigencia;
	}

	public Timestamp getNbmeDtVigenciaFinal() {
		return nbmeDtVigenciaFinal;
	}

	public void setNbmeDtVigenciaFinal(Timestamp nbmeDtVigenciaFinal) {
		this.nbmeDtVigenciaFinal = nbmeDtVigenciaFinal;
	}

	public Float getNbmePercSubstrib() {
		return nbmePercSubstrib;
	}

	public void setNbmePercSubstrib(Float nbmePercSubstrib) {
		this.nbmePercSubstrib = nbmePercSubstrib;
	}

	public Float getNbmePercMargemLucro() {
		return nbmePercMargemLucro;
	}

	public void setNbmePercMargemLucro(Float nbmePercMargemLucro) {
		this.nbmePercMargemLucro = nbmePercMargemLucro;
	}

	public Float getNbmePercIcms() {
		return nbmePercIcms;
	}

	public void setNbmePercIcms(Float nbmePercIcms) {
		this.nbmePercIcms = nbmePercIcms;
	}

	public Float getNbmeBaseIcms() {
		return nbmeBaseIcms;
	}

	public void setNbmeBaseIcms(Float nbmeBaseIcms) {
		this.nbmeBaseIcms = nbmeBaseIcms;
	}

	public Float getNbmePercRedIcms() {
		return nbmePercRedIcms;
	}

	public void setNbmePercRedIcms(Float nbmePercRedIcms) {
		this.nbmePercRedIcms = nbmePercRedIcms;
	}

	public String getNbmeUsuario() {
		return nbmeUsuario;
	}

	public void setNbmeUsuario(String nbmeUsuario) {
		this.nbmeUsuario = nbmeUsuario;
	}

	public Timestamp getNbmeDatahora() {
		return nbmeDatahora;
	}

	public void setNbmeDatahora(Timestamp nbmeDatahora) {
		this.nbmeDatahora = nbmeDatahora;
	}

	public String getNbmeInConvenio() {
		return nbmeInConvenio;
	}

	public void setNbmeInConvenio(String nbmeInConvenio) {
		this.nbmeInConvenio = nbmeInConvenio;
	}

	public String getNbmeInDifalSt() { return nbmeInDifalSt; }

	public void setNbmeInDifalSt(String nbmeInDifalSt) { this.nbmeInDifalSt = nbmeInDifalSt; }

	public String getNbmeIdEstadoDe() {
		return nbmeIdEstadoDe;
	}

	public void setNbmeIdEstadoDe(String nbmeIdEstadoDe) {
		this.nbmeIdEstadoDe = nbmeIdEstadoDe;
	}

	public Integer getNbmeIdCia() {
		return nbmeIdCia;
	}

	public void setNbmeIdCia(Integer nbmeIdCia) {
		this.nbmeIdCia = nbmeIdCia;
	}
	
	public String getNbmeSentido() {
		return nbmeSentido;
	}
	
	public void setNbmeSentido(String nbmeSentido) {
		this.nbmeSentido = nbmeSentido;
	}

}
