package com.b2w.tax.model;

public enum TipoImposto {
	
	ORIGEM("Origem"),
	DESTINO("Destino"),
	INTERESTADUAL("Interestadual");
	
	String value;
	
	private TipoImposto(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
