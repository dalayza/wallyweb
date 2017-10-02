package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DS_DIFAL", schema="USU_PRICE")
public class DifalRule {

	@Id
	private Timestamp vigenciaIni;
	private Timestamp vigenciaFim;
	private Float percentDestino;
	
	public Timestamp getVigenciaIni() {
		return vigenciaIni;
	}
	public Float getPercentDestino() {
		return percentDestino;
	}
	public Timestamp getVigenciaFim() {
		return vigenciaFim;
	}
}
