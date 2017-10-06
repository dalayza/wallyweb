package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DS_NBM_EXCECAO", schema="USU_PRICE")
public class NbmExcecao {
	
	@Id
	private Integer nbexIdNbmPai;
	
	private Integer nbexSeqNbmPai;
	
	private Integer nbexIdNbmFilho;
	
	private Integer nbexSeqNbmFilho;
	
	private String nbexUsuario;
	
	private Timestamp nbexDatahora;
	
	private Integer nbexVlItemExcecao;
	
	private Integer nbexIdExcecao;
	
	private Integer nbexIdCia;

	public Integer getNbexIdNbmPai() {
		return nbexIdNbmPai;
	}

	public void setNbexIdNbmPai(Integer nbexIdNbmPai) {
		this.nbexIdNbmPai = nbexIdNbmPai;
	}

	public Integer getNbexSeqNbmPai() {
		return nbexSeqNbmPai;
	}

	public void setNbexSeqNbmPai(Integer nbexSeqNbmPai) {
		this.nbexSeqNbmPai = nbexSeqNbmPai;
	}

	public Integer getNbexIdNbmFilho() {
		return nbexIdNbmFilho;
	}

	public void setNbexIdNbmFilho(Integer nbexIdNbmFilho) {
		this.nbexIdNbmFilho = nbexIdNbmFilho;
	}

	public Integer getNbexSeqNbmFilho() {
		return nbexSeqNbmFilho;
	}

	public void setNbexSeqNbmFilho(Integer nbexSeqNbmFilho) {
		this.nbexSeqNbmFilho = nbexSeqNbmFilho;
	}

	public String getNbexUsuario() {
		return nbexUsuario;
	}

	public void setNbexUsuario(String nbexUsuario) {
		this.nbexUsuario = nbexUsuario;
	}

	public Timestamp getNbexDatahora() {
		return nbexDatahora;
	}

	public void setNbexDatahora(Timestamp nbexDatahora) {
		this.nbexDatahora = nbexDatahora;
	}

	public Integer getNbexVlItemExcecao() {
		return nbexVlItemExcecao;
	}

	public void setNbexVlItemExcecao(Integer nbexVlItemExcecao) {
		this.nbexVlItemExcecao = nbexVlItemExcecao;
	}

	public Integer getNbexIdExcecao() {
		return nbexIdExcecao;
	}

	public void setNbexIdExcecao(Integer nbexIdExcecao) {
		this.nbexIdExcecao = nbexIdExcecao;
	}

	public Integer getNbexIdCia() {
		return nbexIdCia;
	}

	public void setNbexIdCia(Integer nbexIdCia) {
		this.nbexIdCia = nbexIdCia;
	}

}
