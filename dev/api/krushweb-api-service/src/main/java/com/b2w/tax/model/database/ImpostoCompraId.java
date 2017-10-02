package com.b2w.tax.model.database;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.IdClass;

@IdClass(ImpostoCompraId.class)
public class ImpostoCompraId implements Serializable {

	private String regiao;
	private String classificacao;
	private Integer utilizacao;
	private Integer cfop;
	private Timestamp dataInicio;
	
	public ImpostoCompraId() {
		// TODO Auto-generated constructor stub
	}
	
	public ImpostoCompraId(String regiao, String classificacao, Integer utilizacao, Integer cfop, Timestamp dataInicio){
		this.regiao = regiao;
		this.classificacao = classificacao;
		this.utilizacao = utilizacao;
		this.cfop = cfop;
		this.dataInicio = dataInicio;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ImpostoCompraId){
			ImpostoCompraId impostoCompraId = (ImpostoCompraId) obj;
			if(impostoCompraId.getCfop().equals(this.cfop) && impostoCompraId.getClassificacao().equals(this.classificacao) && impostoCompraId.getDataInicio().equals(this.dataInicio) && impostoCompraId.getRegiao().equals(this.regiao) && impostoCompraId.getUtilizacao().equals(this.utilizacao)){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.regiao.hashCode() + this.classificacao.hashCode() + this.cfop.hashCode() + this.utilizacao.hashCode() + this.dataInicio.hashCode();
	}
	
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
