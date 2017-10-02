package com.b2w.tax.model;

import java.security.InvalidParameterException;

public enum TipoCliente {

	FISICA(1), JURIDICA(2), JURIDICA_ISENTO_IE(3);

	private Integer tipo;

	private TipoCliente(Integer tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return this.tipo;
	}

	public static TipoCliente parse(int tipoClienteInt) {
		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (tipoCliente.getTipo() == tipoClienteInt) {
				return tipoCliente;
			}
		}
		throw new InvalidParameterException("Valor inválido para tipoClienteInt. Valores válidos são 1, 2 e 3.");
	}

}
