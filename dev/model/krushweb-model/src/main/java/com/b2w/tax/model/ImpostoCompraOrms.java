package com.b2w.tax.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class ImpostoCompraOrms  {

	protected Float iva;
	protected Float aliqIpi;
	protected Float baseIpi;
	protected Float aliqPis;
	protected Float aliqCofins;
	protected Float aliqIcms;
	protected Float baseIcms;
	@Transient
	protected Float aliqIcmsFinal;
	protected Float aliqIcmsst;
	protected Float baseIcmsst;
	@Transient
	protected Float aliqIcmsstFinal;
	
	public Float getIva() {
		return iva;
	}
	public void setIva(Float iva) {
		this.iva = iva;
	}
	public Float getAliqIpi() {
		return aliqIpi;
	}
	public void setAliqIpi(Float aliqIpi) {
		this.aliqIpi = aliqIpi;
	}
	public Float getBaseIpi() {
		return baseIpi;
	}
	public void setBaseIpi(Float baseIpi) {
		this.baseIpi = baseIpi;
	}
	public Float getAliqPis() {
		return aliqPis;
	}
	public void setAliqPis(Float aliqPis) {
		this.aliqPis = aliqPis;
	}
	public Float getAliqCofins() {
		return aliqCofins;
	}
	public void setAliqCofins(Float aliqCofins) {
		this.aliqCofins = aliqCofins;
	}
	public Float getAliqIcms() {
		return aliqIcms;
	}
	public void setAliqIcms(Float aliqIcms) {
		this.aliqIcms = aliqIcms;
	}
	public Float getBaseIcms() {
		return baseIcms;
	}
	public void setBaseIcms(Float baseIcms) {
		this.baseIcms = baseIcms;
	}
	public Float getAliqIcmsst() {
		return aliqIcmsst;
	}
	public void setAliqIcmsst(Float aliqIcmsst) {
		this.aliqIcmsst = aliqIcmsst;
	}
	public Float getBaseIcmsst() {
		return baseIcmsst;
	}
	public void setBaseIcmsst(Float baseIcmsst) {
		this.baseIcmsst = baseIcmsst;
	}
	public Float getAliqIcmsFinal() {
		return aliqIcmsFinal;
	}
	public void setAliqIcmsFinal(Float aliqIcmsFinal) {
		this.aliqIcmsFinal = aliqIcmsFinal;
	}
	public Float getAliqIcmsstFinal() {
		return aliqIcmsstFinal;
	}
	public void setAliqIcmsstFinal(Float aliqIcmsstFinal) {
		this.aliqIcmsstFinal = aliqIcmsstFinal;
	}
	
}
