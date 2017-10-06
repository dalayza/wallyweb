package com.b2w.tax.model.database;

import java.io.Serializable;

import javax.persistence.IdClass;

@IdClass(NbmSeqIcne.class)
public class NbmSeqIcne implements Serializable {

	private Long icneIdNbm;

	private String icneIdEstadoDe;

	private String icneIdEstadoPara;

	private Integer icneIdCia;

	public NbmSeqIcne() {
		// TODO Auto-generated constructor stub
	}

	public NbmSeqIcne(Long icneIdNbm, String icneIdEstadoDe, String icneIdEstadoPara, Integer icneIdCia) {
		this.icneIdNbm = icneIdNbm;
		this.icneIdEstadoDe = icneIdEstadoDe;
		this.icneIdEstadoPara = icneIdEstadoPara;
		this.icneIdCia = icneIdCia;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NbmSeqIcne) {
			NbmSeqIcne nbmSeqId = (NbmSeqIcne) obj;
			if (this.icneIdNbm.equals(nbmSeqId.getIcneIdNbm())
					&& this.icneIdEstadoDe.equals(nbmSeqId.getIcneIdEstadoDe())
					&& this.icneIdEstadoPara.equals(nbmSeqId.getIcneIdEstadoPara())
					&& this.icneIdCia.equals(nbmSeqId.getIcneIdCia())) {
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

	public Long getIcneIdNbm() {
		return icneIdNbm;
	}

	public void setIcneIdNbm(Long icneIdNbm) {
		this.icneIdNbm = icneIdNbm;
	}

	public String getIcneIdEstadoDe() {
		return icneIdEstadoDe;
	}

	public void setIcneIdEstadoDe(String icneIdEstadoDe) {
		this.icneIdEstadoDe = icneIdEstadoDe;
	}

	public String getIcneIdEstadoPara() {
		return icneIdEstadoPara;
	}

	public void setIcneIdEstadoPara(String icneIdEstadoPara) {
		this.icneIdEstadoPara = icneIdEstadoPara;
	}

	public Integer getIcneIdCia() {
		return icneIdCia;
	}

	public void setIcneIdCia(Integer icneIdCia) {
		this.icneIdCia = icneIdCia;
	}

}