package com.b2w.tax.model;

public class ImpostoVenda extends Impostos{

	private Icms icmsVenda;
	private BaseReduzida baseReduzida;
	private Icms icmsFinal;
	private Icms icmsSt;
	
	public Icms getIcmsVenda() {
		return icmsVenda;
	}
	public void setIcmsVenda(Icms icmsVenda) {
		this.icmsVenda = icmsVenda;
	}
	public BaseReduzida getBaseReduzida() {
		return baseReduzida;
	}
	public void setBaseReduzida(BaseReduzida baseReduzida) {
		this.baseReduzida = baseReduzida;
	}
	public Icms getIcmsFinal() {
		return icmsFinal;
	}
	public void setIcmsFinal(Icms icmsFinal) {
		this.icmsFinal = icmsFinal;
	}
	public Icms getIcmsSt() {
		return icmsSt;
	}
	public void setIcmsSt(Icms icmsSt) {
		this.icmsSt = icmsSt;
	}
}
