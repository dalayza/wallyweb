package com.b2w.tax.model.database;

import java.io.Serializable;

import javax.persistence.IdClass;

@IdClass(NbmEstadoId.class)
public class NbmEstadoId implements Serializable{
	
	private Long nbmeIdNbm;
	
	private Integer nbmeSeqNbm;
	
	private String nbmeIdEstadoDe;
	
	private String nbmeIdEstadoPara;
	
	private Integer nbmeIdCia;
	
	public NbmEstadoId() {
		// TODO Auto-generated constructor stub
	}
	
	public NbmEstadoId(Long nbmeIdNbm, Integer nbmeSeqNbm, String nbmeIdEstadoDe, String nbmeIdEstadoPara, Integer nbmeIdCia){
		this.nbmeIdNbm = nbmeIdNbm;
		this.nbmeIdEstadoDe = nbmeIdEstadoDe;
		this.nbmeIdEstadoPara = nbmeIdEstadoPara;
		this.nbmeIdCia = nbmeIdCia;
		this.nbmeSeqNbm = nbmeSeqNbm;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NbmEstadoId){
			NbmEstadoId nbmEstadoId = (NbmEstadoId) obj;
			if(this.nbmeIdNbm.equals(nbmEstadoId.getNbmeIdNbm()) && this.nbmeIdEstadoDe.equals(nbmEstadoId.getNbmeIdEstadoDe()) && this.nbmeIdEstadoPara.equals(nbmEstadoId.getNbmeIdEstadoPara()) && this.nbmeIdCia.equals(nbmEstadoId.getNbmeIdCia()) && this.nbmeSeqNbm.equals(nbmEstadoId.getNbmeSeqNbm())){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public Long getNbmeIdNbm() {
		return nbmeIdNbm;
	}

	public void setNbmeIdNbm(Long nbmeIdNbm) {
		this.nbmeIdNbm = nbmeIdNbm;
	}

	public Integer getNbmeSeqNbm() {
		return nbmeSeqNbm;
	}

	public void setNbmeSeqNbm(Integer nbmeSeqNbm) {
		this.nbmeSeqNbm = nbmeSeqNbm;
	}

	public String getNbmeIdEstadoDe() {
		return nbmeIdEstadoDe;
	}

	public void setNbmeIdEstadoDe(String nbmeIdEstadoDe) {
		this.nbmeIdEstadoDe = nbmeIdEstadoDe;
	}

	public String getNbmeIdEstadoPara() {
		return nbmeIdEstadoPara;
	}

	public void setNbmeIdEstadoPara(String nbmeIdEstadoPara) {
		this.nbmeIdEstadoPara = nbmeIdEstadoPara;
	}

	public Integer getNbmeIdCia() {
		return nbmeIdCia;
	}

	public void setNbmeIdCia(Integer nbmeIdCia) {
		this.nbmeIdCia = nbmeIdCia;
	}

	
	
	

}
