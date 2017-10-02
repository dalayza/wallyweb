package com.b2w.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Impostos {

	@JsonInclude(Include.NON_NULL)
	private Float cofins;
	private Float cofinsPercent;
	@JsonInclude(Include.NON_NULL)
	private Float pis;
	private Float pisPercent;
	@JsonInclude(Include.NON_NULL)
	private Float valorBase;
	
	public Float getPis() {
		return pis;
	}
	public void setPis(Float pis) {
		this.pis = pis;
	}
	public Float getCofins() {
		return cofins;
	}
	public void setCofins(Float cofins) {
		this.cofins = cofins;
	}
	public Float getValorBase() {
		return valorBase;
	}
	public void setValorBase(Float valorBase) {
		this.valorBase = valorBase;
	}
	public Float getCofinsPercent() {
		return cofinsPercent;
	}
	public void setCofinsPercent(Float cofinsPercent) {
		this.cofinsPercent = cofinsPercent;
	}
	public Float getPisPercent() {
		return pisPercent;
	}
	public void setPisPercent(Float pisPercent) {
		this.pisPercent = pisPercent;
	}
}
