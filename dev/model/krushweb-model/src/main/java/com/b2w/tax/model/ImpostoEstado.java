package com.b2w.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ImpostoEstado {
	
	private String ufOrigem;
	private String ufDestino;
	@JsonInclude(Include.NON_NULL)
	private Float valorBase;
	@JsonInclude(Include.NON_NULL)
	private TipoImposto tipoImposto;

	public Float getValorBase() {
		return valorBase;
	}
	public void setValorBase(Float valorBase) {
		this.valorBase = valorBase;
	}
	public String getUfOrigem() {
		return ufOrigem;
	}
	public void setUfOrigem(String ufOrigem) {
		this.ufOrigem = ufOrigem;
	}
	public String getUfDestino() {
		return ufDestino;
	}
	public void setUfDestino(String ufDestino) {
		this.ufDestino = ufDestino;
	}
	public TipoImposto getTipoImposto() {
		return tipoImposto;
	}
	public void setTipoImposto(TipoImposto tipoImposto) {
		this.tipoImposto = tipoImposto;
	}
}
