package com.b2w.tax.model.database;

import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Cacheable("taxes")
@Table(name="DS_FILIAL_REGIME_ESPECIAL", schema = "USU_PRICE")
public class FilialRegimeEspecial {

    @Id
    @Column(name = "FREE_ID_FILIAL")
    private Integer idFilial;
    @Column(name = "FREE_ID_CIA")
    private Integer idCia;
    private String freeVariavel;
    private Timestamp freeDatahora;
    private String freeInRegime;

    public Integer getIdCia() {
        return idCia;
    }

    public void setIdCia(Integer idCia) {
        this.idCia = idCia;
    }

    public Integer getIdFilial() {
        return idFilial;
    }

    public String getFreeVariavel() {
        return freeVariavel;
    }

    public void setFreeVariavel(String freeVariavel) {
        this.freeVariavel = freeVariavel;
    }

    public Timestamp getFreeDatahora() {
        return freeDatahora;
    }

    public void setFreeDatahora(Timestamp freeDatahora) {
        this.freeDatahora = freeDatahora;
    }

    public String getFreeInRegime() {
        return freeInRegime;
    }

    public void setFreeInRegime(String freeInRegime) {
        this.freeInRegime = freeInRegime;
    }
}
