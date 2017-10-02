package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.b2w.tax.model.ImpostoCompraOrms;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="DS_IMPOSTO_COMPRA", schema="USU_PRICE")
@IdClass(ImpostoCompraId.class)
public class ImpostoCompraDb extends ImpostoCompraOrms {
	
	@JsonIgnore
	@Id
	private String regiao;
	@JsonIgnore
	@Id
	private String classificacao;
	@JsonIgnore
	@Id
	private Integer utilizacao;
	@JsonIgnore
	@Id
	private Integer cfop;
	@JsonIgnore
	@Id
	private Timestamp dataInicio;
	
	
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public Integer getUtilizacao() {
		return utilizacao;
	}
	public void setUtilizacao(Integer utilizacao) {
		this.utilizacao = utilizacao;
	}
	public Integer getCfop() {
		return cfop;
	}
	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}
	public Timestamp getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}
}