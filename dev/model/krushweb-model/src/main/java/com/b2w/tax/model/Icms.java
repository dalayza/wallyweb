package com.b2w.tax.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Icms extends ImpostoEstado {
	
	public Icms(){
	}
	
	public Icms(String ufOrigem, String ufDestino, Float icmsPercent, TipoImposto tipoImposto) {
		this.setUfOrigem(ufOrigem);
		this.setUfDestino(ufDestino);
		this.setIcmsPercent(icmsPercent);
		this.setTipoImposto(tipoImposto);
	}

	
	public Icms(String ufOrigem, String ufDestino, Float icmsPercent) {
		this.setUfOrigem(ufOrigem);
		this.setUfDestino(ufDestino);
		this.setIcmsPercent(icmsPercent);
	}

    public Icms(String ufOrigem, String ufDestino, Float icmsPercent, Float icms, Float valorBase) {
        this.setUfOrigem(ufOrigem);
        this.setUfDestino(ufDestino);
        this.setIcmsPercent(icmsPercent);
        this.setIcms(icms);
        this.setValorBase(valorBase);
    }

    public Icms(String ufOrigem, String ufDestino, Float icmsPercent, Float icms, Float valorBase, TipoImposto tipoImposto) {
        this.setUfOrigem(ufOrigem);
        this.setUfDestino(ufDestino);
        this.setIcmsPercent(icmsPercent);
        this.setIcms(icms);
        this.setValorBase(valorBase);
        this.setTipoImposto(tipoImposto);
    }

	@JsonInclude(Include.NON_NULL)
	private Float icms;
	private Float icmsPercent;
	
	public Float getIcms() {
		return icms;
	}
	
	public void setIcms(Float icms) {
		this.icms = icms;
	}
	
	public Float getIcmsPercent() {
		return icmsPercent;
	}
	
	public void setIcmsPercent(Float icmsPercent) {
		this.icmsPercent = icmsPercent;
	}
	

	
}
