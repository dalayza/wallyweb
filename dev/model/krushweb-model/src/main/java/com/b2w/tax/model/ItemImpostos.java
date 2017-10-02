package com.b2w.tax.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ItemImpostos {
	
	private Long sku;
	private Long ncm;
	@JsonInclude(Include.NON_NULL)
	private ImpostosVenda impostosVendas;
	@JsonInclude(Include.NON_NULL)
	private ImpostoVenda impostoVenda;
	@JsonInclude(Include.NON_NULL)
	private ImpostosCompra impostosCompra;
	
	public ItemImpostos() {
	}
	
	public ItemImpostos(Long sku, Long ncm, ImpostosVenda impostosVenda){
		this.sku = sku;
		this.ncm = ncm;
		this.impostosVendas = impostosVenda;
	}
	
	public ItemImpostos(Long sku, Long ncm, ImpostosVenda impostosVenda, ImpostosCompra impostosCompra){
		this.sku = sku;
		this.ncm = ncm;
		this.impostosVendas = impostosVenda;
		this.impostosCompra = impostosCompra;
	}
	
/*	public ItemImpostos(Long sku, Long ncm, ImpostoVenda impostoVenda){
		this.sku = sku;
		this.ncm = ncm;
		this.impostoVenda = impostoVenda;
	}*/
	
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public Long getNcm() {
		return ncm;
	}
	public void setNcm(Long ncm) {
		this.ncm = ncm;
	}
	public ImpostosVenda getImpostosVendas() {
		return impostosVendas;
	}
	public void setImpostosVendas(ImpostosVenda impostosVendas) {
		this.impostosVendas = impostosVendas;
	}
	public ImpostosCompra getImpostosCompra() {
		return impostosCompra;
	}
	public void setImpostosCompra(ImpostosCompra impostosCompra) {
		this.impostosCompra = impostosCompra;
	}
	
	public ImpostoVenda getImpostoVenda() {
		return impostoVenda;
	}
	public void setImpostoVenda(ImpostoVenda impostoVenda) {
		this.impostoVenda = impostoVenda;
	}
	
}
