package com.b2w.tax.exception;

import java.util.List;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Não foi possível encontrar este imposto. Verifique a parametrização fiscal para o item em questão.")
public class TaxNotFoundException extends TaxException {


    public TaxNotFoundException() {super();}

    public TaxNotFoundException(String message) {
		super(message);
	}

    public TaxNotFoundException(String message, String ncm, String origem, String destino) { super(message, ncm, origem, destino);}

    public TaxNotFoundException(String message, List<String> skus){super(message, skus); }

}
