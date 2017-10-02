package com.b2w.tax.model;

public enum TipoFornecedor {

	DISTRIBUIDOR("Distribuidor"), 
	INDUSTRIALIZADOR("Industrializador");

	private TipoFornecedor(String tipo) {
		this.tipo = tipo;
	}

	public static TipoFornecedor getByValue(String value) {
		if (null != value) {
			for (TipoFornecedor tipoFornecedor : TipoFornecedor.values()) {
				if (value.toUpperCase().equals(tipoFornecedor.tipo.toUpperCase())) {
					return tipoFornecedor;
				}
			}
		}
		throw new IllegalArgumentException("No enum with value " + value);
	}

	@Override
	public String toString() {
		return this.tipo;
	}

	private String tipo;

}
