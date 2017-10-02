package com.b2w.tax.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ImpostosVenda extends Impostos{
	
	
	private List<Icms> icmsVendas;
	private List<BaseReduzida> baseReduzidas;
	@JsonInclude(Include.NON_NULL)
	private List<Icms> icmsFinais;
	@JsonInclude(Include.NON_NULL)
	private List<Icms> icmsSts;

	public List<Icms> getIcmsVendas() {
		return icmsVendas;
	}
	public void setIcmsVendas(List<Icms> icmsVendas) {
		this.icmsVendas = icmsVendas;
	}
	public List<BaseReduzida> getBaseReduzidas() {
		return baseReduzidas;
	}
	public void setBaseReduzidas(List<BaseReduzida> baseReduzidas) {
		this.baseReduzidas = baseReduzidas;
	}
	public List<Icms> getIcmsFinais() {
		return icmsFinais;
	}
	public void setIcmsFinais(List<Icms> icmsFinais) {
		this.icmsFinais = icmsFinais;
	}
	public List<Icms> getIcmsSts() {
		return icmsSts;
	}
	public void setIcmsSts(List<Icms> icmsSts) {
		this.icmsSts = icmsSts;
	}


}
