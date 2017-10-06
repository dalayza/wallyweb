package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable("taxes")
@Table(name = "DS_ICMS_ESTADOS", schema = "USU_PRICE")
@IdClass(IcmsEstadosId.class)
public class IcmsEstados {

    @Id
    @Column(name = "ICME_ID_ESTADO_DE")
    private String icmeIdEstadoDe;

    @Id
    @Column(name = "ICME_ID_ESTADO_PARA")
    private String icmeIdEstadoPara;


    @Column(name = "ICME_PE_ICMS")
    private Float icmePeIcms;

    @Column(name = "ICME_PE_ICMS_FRETE")
    private Float icmePeIcmsFrete;

    @Column(name = "ICME_DATAHORA")
    private Timestamp icmeDatahora;

    @Column(name = "ICME_USUARIO")
    private String icmeUsuario;

    @Column(name = "ICME_PE_ICMS_FECP")
    private Float icmePercIcmsFecp;

    public String getIcmeIdEstadoDe() {
        return icmeIdEstadoDe;
    }

    public void setIcmeIdEstadoDe(String icmeIdEstadoDe) {
        this.icmeIdEstadoDe = icmeIdEstadoDe;
    }

    public String getIcmeIdEstadoPara() {
        return icmeIdEstadoPara;
    }

    public void setIcmeIdEstadoPara(String icmeIdEstadoPara) {
        this.icmeIdEstadoPara = icmeIdEstadoPara;
    }

    public Float getIcmePeIcms() {
        return icmePeIcms;
    }

    public void setIcmePeIcms(Float icmePeIcms) {
        this.icmePeIcms = icmePeIcms;
    }

    public Float getIcmePeIcmsFrete() {
        return icmePeIcmsFrete;
    }

    public void setIcmePeIcmsFrete(Float icmePeIcmsFrete) {
        this.icmePeIcmsFrete = icmePeIcmsFrete;
    }

    public Timestamp getIcmeDatahora() {
        return icmeDatahora;
    }

    public void setIcmeDatahora(Timestamp icmeDatahora) {
        this.icmeDatahora = icmeDatahora;
    }

    public String getIcmeUsuario() {
        return icmeUsuario;
    }

    public void setIcmeUsuario(String icmeUsuario) {
        this.icmeUsuario = icmeUsuario;
    }

    public Float getIcmePercIcmsFecp() {
        return icmePercIcmsFecp;
    }

    public void setIcmePercIcmsFecp(Float icmePercIcmsFecp) {
        this.icmePercIcmsFecp = icmePercIcmsFecp;
    }

}
