package com.b2w.tax.model.database;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.cache.annotation.Cacheable;

/**
 * 
 * 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "DS_NBM", uniqueConstraints = @UniqueConstraint(columnNames = { "nbmIdNbm", "nbmSeq",
		"nbmIdCia" }), schema = "USU_PRICE")
@IdClass(NbmSeqId.class)
@Cacheable("taxes")
public class Nbm implements Serializable {

	@Id
	private Long nbmIdNbm;

	@Id
	private Integer nbmSeq;

	@Id
	private Integer nbmIdCia;

	private String nbmDescricao;

	private Float nbmPercIpi;

	private Float nbmVlPauta;

	private String nbmGatt;

	private String nbmNaladi;

	private String nbmUsuario;

	private String nbmIdUnidadeFiscal;

	private Timestamp nbmDtUltalt;

	private String nbmSituacao;

	private Float nbmPercPis;

	private Float nbmPercCofins;

	private Float nbmPercPisSai;

	private Float nbmPercCofinsSai;

	private Float nbmVlItemExcecao;

	private String nbmInMonofasico;

	private String nbmSeqExterno;

	private Timestamp nbmDtVigenciaIni;

	private Timestamp nbmDtVigenciaFim;

	public Long getNbmIdNbm() {
		return nbmIdNbm;
	}

	public void setNbmIdNbm(Long nbmIdNbm) {
		this.nbmIdNbm = nbmIdNbm;
	}

	public Integer getNbmSeq() {
		return nbmSeq;
	}

	public void setNbmSeq(Integer nbmSeq) {
		this.nbmSeq = nbmSeq;
	}

	public String getNbmDescricao() {
		return nbmDescricao;
	}

	public void setNbmDescricao(String nbmDescricao) {
		this.nbmDescricao = nbmDescricao;
	}

	public Float getNbmPercIpi() {
		return nbmPercIpi;
	}

	public void setNbmPercIpi(Float nbmPercIpi) {
		this.nbmPercIpi = nbmPercIpi;
	}

	public Float getNbmVlPauta() {
		return nbmVlPauta;
	}

	public void setNbmVlPauta(Float nbmVlPauta) {
		this.nbmVlPauta = nbmVlPauta;
	}

	public String getNbmGatt() {
		return nbmGatt;
	}

	public void setNbmGatt(String nbmGatt) {
		this.nbmGatt = nbmGatt;
	}

	public String getNbmNaladi() {
		return nbmNaladi;
	}

	public void setNbmNaladi(String nbmNaladi) {
		this.nbmNaladi = nbmNaladi;
	}

	public String getNbmUsuario() {
		return nbmUsuario;
	}

	public void setNbmUsuario(String nbmUsuario) {
		this.nbmUsuario = nbmUsuario;
	}

	public String getNbmIdUnidadeFiscal() {
		return nbmIdUnidadeFiscal;
	}

	public void setNbmIdUnidadeFiscal(String nbmIdUnidadeFiscal) {
		this.nbmIdUnidadeFiscal = nbmIdUnidadeFiscal;
	}

	public Timestamp getNbmDtUltalt() {
		return nbmDtUltalt;
	}

	public void setNbmDtUltalt(Timestamp nbmDtUltalt) {
		this.nbmDtUltalt = nbmDtUltalt;
	}

	public String getNbmSituacao() {
		return nbmSituacao;
	}

	public void setNbmSituacao(String nbmSituacao) {
		this.nbmSituacao = nbmSituacao;
	}

	public Float getNbmPercPis() {
		return nbmPercPis;
	}

	public void setNbmPercPis(Float nbmPercPis) {
		this.nbmPercPis = nbmPercPis;
	}

	public Float getNbmPercCofins() {
		return nbmPercCofins;
	}

	public void setNbmPercCofins(Float nbmPercCofins) {
		this.nbmPercCofins = nbmPercCofins;
	}

	public Float getNbmPercPisSai() {
		return nbmPercPisSai;
	}

	public void setNbmPercPisSai(Float nbmPercPisSai) {
		this.nbmPercPisSai = nbmPercPisSai;
	}

	public Float getNbmPercCofinsSai() {
		return nbmPercCofinsSai;
	}

	public void setNbmPercCofinsSai(Float nbmPercCofinsSai) {
		this.nbmPercCofinsSai = nbmPercCofinsSai;
	}

	public Float getNbmVlItemExcecao() {
		return nbmVlItemExcecao;
	}

	public void setNbmVlItemExcecao(Float nbmVlItemExcecao) {
		this.nbmVlItemExcecao = nbmVlItemExcecao;
	}

	public String getNbmInMonofasico() {
		return nbmInMonofasico;
	}

	public void setNbmInMonofasico(String nbmInMonofasico) {
		this.nbmInMonofasico = nbmInMonofasico;
	}

	public Integer getNbmIdCia() {
		return nbmIdCia;
	}

	public void setNbmIdCia(Integer nbmIdCia) {
		this.nbmIdCia = nbmIdCia;
	}

	public String getNbmSeqExterno() {
		return nbmSeqExterno;
	}

	public void setNbmSeqExterno(String nbmSeqExterno) {
		this.nbmSeqExterno = nbmSeqExterno;
	}

	public Timestamp getNbmDtVigenciaFim() {
		return nbmDtVigenciaFim;
	}

	public void setNbmDtVigenciaFim(Timestamp nbmDtVigenciaFim) {
		this.nbmDtVigenciaFim = nbmDtVigenciaFim;
	}

	public Timestamp getNbmDtVigenciaIni() {
		return nbmDtVigenciaIni;
	}

	public void setNbmDtVigenciaIni(Timestamp nbmDtVigenciaIni) {
		this.nbmDtVigenciaIni = nbmDtVigenciaIni;
	}

}
