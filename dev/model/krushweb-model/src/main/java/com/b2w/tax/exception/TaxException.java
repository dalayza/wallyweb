package com.b2w.tax.exception;

import java.util.List;

/**
 * Created by luiz.costa on 27/06/16.
 */
public class TaxException extends Exception {

    private String ncm;
    private String origem;
    private String destino;
    private List<String> skus;

    public TaxException() {
        super();
    }

    public TaxException(String message) {
        super(message);
    }

    public TaxException(String message, List<String> skus) {
        super(message);
        this.skus = skus;
    }

    public TaxException(String message, String ncm, String origem, String destino) {
        super(message);
        this.ncm = ncm;
        this.origem = origem;
        this.destino = destino;
    }

    public String getNcm() {
        return ncm;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public List<String> getSkus() {
        return skus;
    }
}
