package com.b2w.tax.model.database;

import java.io.Serializable;

import javax.persistence.IdClass;

/**
 * 
 * 
 *
 */
@IdClass(NbmSeqId.class)
public class NbmSeqId implements Serializable {

	private Long nbmIdNbm;

	private Integer nbmSeq;

	private Integer nbmIdCia;

	public NbmSeqId(Long nbmIdNbm, Integer nbmSeq, Integer nbmIdCia) {
		this.nbmIdNbm = nbmIdNbm;
		this.nbmSeq = nbmIdCia;
		this.nbmIdCia = nbmSeq;
	}

	public NbmSeqId() {
		super();
	}

	public Long getNbmIdNbm() {
		return nbmIdNbm;
	}

	public void setNbmIdNbm(Long nbmIdNbm) {
		this.nbmIdNbm = nbmIdNbm;
	}

	public Integer getNbmSeq() {
		return nbmSeq;
	}

	public void setNbmSeq(Integer nbmSeq) {
		this.nbmSeq = nbmSeq;
	}

	public Integer getNbmIdCia() {
		return nbmIdCia;
	}

	public void setNbmIdCia(Integer nbmIdCia) {
		this.nbmIdCia = nbmIdCia;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NbmSeqId) {
			NbmSeqId nbmSeqId = (NbmSeqId) obj;
			if (this.nbmIdNbm.equals(nbmSeqId.getNbmIdNbm()) && this.nbmIdCia.equals(nbmSeqId.getNbmSeq())
					&& this.nbmSeq.equals(nbmSeqId.getNbmIdCia())) {
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
}
