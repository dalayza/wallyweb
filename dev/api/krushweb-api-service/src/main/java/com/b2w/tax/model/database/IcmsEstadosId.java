package com.b2w.tax.model.database;

import java.io.Serializable;

public class IcmsEstadosId implements Serializable {

	private String icmeIdEstadoDe;
	private String icmeIdEstadoPara;
	
	public IcmsEstadosId() {
		// TODO Auto-generated constructor stub
	}
	
	public IcmsEstadosId(String icmeIdEstadoDe, String icmeIdEstadoPara){
		this.icmeIdEstadoDe = icmeIdEstadoDe;
		this.icmeIdEstadoPara = icmeIdEstadoPara;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof IcmsEstadosId){
			IcmsEstadosId icmsEstadosId = (IcmsEstadosId) arg0;
			return icmsEstadosId.getIcmeIdEstadoDe().equals(this.icmeIdEstadoDe) && icmsEstadosId.getIcmeIdEstadoPara().equals(this.icmeIdEstadoPara);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.icmeIdEstadoDe.hashCode() + this.icmeIdEstadoPara.hashCode();
	}
	
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

}
