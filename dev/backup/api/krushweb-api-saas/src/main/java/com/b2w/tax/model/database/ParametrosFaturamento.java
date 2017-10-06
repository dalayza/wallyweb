package com.b2w.tax.model.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DS_PARAMETROS_FATURAMENTO", schema="USU_PRICE")
public class ParametrosFaturamento {
	
	@Id
	private Float pafaPercPis;
	private Float pafaPercCofins;
	
	public Float getPafaPercCofins() {
		return pafaPercCofins;
	}
	
	public void setPafaPercCofins(Float pafaPercCofins) {
		this.pafaPercCofins = pafaPercCofins;
	}
	
	public Float getPafaPercPis() {
		return pafaPercPis;
	}
	
	public void setPafaPercPis(Float pafaPercPis) {
		this.pafaPercPis = pafaPercPis;
	}

}
