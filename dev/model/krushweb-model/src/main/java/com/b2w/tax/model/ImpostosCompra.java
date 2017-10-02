package com.b2w.tax.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ImpostosCompra extends Impostos {

	private List<Icms> icmsCompras;
	private List<BaseReduzida> baseReduzidas;
	private List<Iva> ivas;
	@JsonInclude(Include.NON_NULL)
	private List<Icms> icmsFinais;
	@JsonInclude(Include.NON_NULL)
	private List<Icms> icmsSts;
	
	public List<Icms> getIcmsCompras() {
		return icmsCompras;
	}
	public void setIcmsCompras(List<Icms> icmsCompras) {
		this.icmsCompras = icmsCompras;
	}
	public List<BaseReduzida> getBaseReduzidas() {
		return baseReduzidas;
	}
	public void setBaseReduzidas(List<BaseReduzida> baseReduzidas) {
		this.baseReduzidas = baseReduzidas;
	}
	public List<Iva> getIvas() {
		return ivas;
	}
	public void setIvas(List<Iva> ivas) {
		this.ivas = ivas;
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
