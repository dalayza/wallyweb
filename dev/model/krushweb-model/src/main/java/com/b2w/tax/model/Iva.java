package com.b2w.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Iva extends ImpostoEstado {
	
	public Iva() {
	}
	
	public Iva(String ufOrigem, String ufDestino, Float ivaPercent, TipoImposto tipoImposto) {
		this.setUfOrigem(ufOrigem);
		this.setUfDestino(ufDestino);
		this.setIvaPercent(ivaPercent);
		this.setTipoImposto(tipoImposto);
	}

	@JsonInclude(Include.NON_NULL)
	private Float iva;
	private Float ivaPercent;
	
	public Float getIva() {
		return iva;
	}
	
	public void setIva(Float iva) {
		this.iva = iva;
	}
	
	public Float getIvaPercent() {
		return ivaPercent;
	}
	
	public void setIvaPercent(Float ivaPercent) {
		this.ivaPercent = ivaPercent;
	}

}
