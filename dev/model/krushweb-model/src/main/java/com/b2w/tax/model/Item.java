package com.b2w.tax.model;

public class Item {

	private Integer fromWarehouse;
	private Long sku;
	private Float valor;
	private Integer quantidade;
	
	public Integer getFromWarehouse() {
		return fromWarehouse;
	}
	public void setFromWarehouse(Integer fromWarehouse) {
		this.fromWarehouse = fromWarehouse;
	}
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
