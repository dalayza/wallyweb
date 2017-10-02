package com.b2w.tax.exception;

public class TaxServiceException extends TaxException {


    public TaxServiceException(){
        super();
    }

	public TaxServiceException(String message) {
		super(message);
	}

    public TaxServiceException(String message, String ncm, String origem, String destino){
        super(message, ncm, origem, destino);
    }

}
