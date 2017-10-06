package com.b2w.tax.model.database;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DS_NATUREZA_DE_OPERACOES", schema="USU_PRICE")
public class NaturezaDeOperacoes {
	
	@Id
	@Column(name="NATO_ID_NATOPE")
	private Integer natoIdNatope;
	
	@Column(name="NATO_NOME")
	private String natoNome;
	
	@Column(name="NATO_IN_DESTINO")
	private String natoInDestino;
	
	@Column(name="NATO_IN_ENTRA_SAI")
	private String natoInEntraSai;
	
	@Column(name="NATO_IN_REMESSA")
	private String natoInRemessa;
	
	@Column(name="NATO_USUARIO")
	private String natoUsuario;
	
	@Column(name="NATO_DT_ALTERACAO")
	private Timestamp natoDtAlteracao;
	
	public Integer getNatoIdNatope() {
		return natoIdNatope;
	}

	public void setNatoIdNatope(Integer natoIdNatope) {
		this.natoIdNatope = natoIdNatope;
	}
	
	public String getNatoNome() {
		return natoNome;
	}

	public void setNatoNome(String natoNome) {
		this.natoNome = natoNome;
	}
	
	public String getNatoInDestino() {
		return natoInDestino;
	}

	public void setNatoInDestino(String natoInDestino) {
		this.natoInDestino = natoInDestino;
	}
	
	public String getNatoInEntraSai() {
		return natoInEntraSai;
	}

	public void setNatoInEntraSai(String natoInEntraSai) {
		this.natoInEntraSai = natoInEntraSai;
	}
	
	public String getNatoInRemessa() {
		return natoInRemessa;
	}

	public void setNatoInRemessa(String natoInRemessa) {
		this.natoInRemessa = natoInRemessa;
	}
	
	public String getNatoUsuario() {
		return natoUsuario;
	}

	public void setNatoUsuario(String natoUsuario) {
		this.natoUsuario = natoUsuario;
	}
	
	public Timestamp getNatoDtAlteracao() {
		return natoDtAlteracao;
	}

	public void setNatoDtAlteracao(Timestamp natoDtAlteracao) {
		this.natoDtAlteracao = natoDtAlteracao;
	}
	
}
