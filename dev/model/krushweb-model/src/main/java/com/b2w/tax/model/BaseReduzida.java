package com.b2w.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BaseReduzida extends ImpostoEstado {
	
	public BaseReduzida(){
	}
	
	public BaseReduzida(String ufOrigem, String ufDestino, Float baseReduzidaPercent, TipoImposto tipoImposto) {
		setUfOrigem(ufOrigem);
		setUfDestino(ufDestino);
		setBaseReduzidaPercent(baseReduzidaPercent);
		setTipoImposto(tipoImposto);
	}
	
	public BaseReduzida(String ufOrigem, String ufDestino, Float baseReduzidaPercent) {
		setUfOrigem(ufOrigem);
		setUfDestino(ufDestino);
		setBaseReduzidaPercent(baseReduzidaPercent);
	}
	
	@JsonInclude(Include.NON_NULL)
	private Float baseReduzida;
	private Float baseReduzidaPercent;
	
	public Float getBaseReduzida() {
		return baseReduzida;
	}
	
	public void setBaseReduzida(Float baseReduzida) {
		this.baseReduzida = baseReduzida;
	}
	
	public Float getBaseReduzidaPercent() {
		return baseReduzidaPercent;
	}
	
	public void setBaseReduzidaPercent(Float baseReduzidaPercent) {
		this.baseReduzidaPercent = baseReduzidaPercent;
	}

}
