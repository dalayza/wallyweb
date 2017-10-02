package com.b2w.tax.model;

/**
 * 
 * 
 * 
 * @author fagner.moura
 *
 */
public class NomenclaturaBrasileiraMercadorias {

	private Long nbm;

	private Integer sequentialUmbrella;

	public NomenclaturaBrasileiraMercadorias(Long nbm, Integer sequentialUmbrella) {
		this.nbm = nbm;
		this.sequentialUmbrella = sequentialUmbrella;
	}

	public Long getNbm() {
		return nbm;
	}

	public void setNbm(Long nbm) {
		this.nbm = nbm;
	}

	public Integer getSequentialUmbrella() {
		return sequentialUmbrella;
	}

	public void setSequentialUmbrella(Integer sequentialUmbrella) {
		this.sequentialUmbrella = sequentialUmbrella;
	}
}
