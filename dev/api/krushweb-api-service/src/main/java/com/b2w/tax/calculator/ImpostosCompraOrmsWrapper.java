package com.b2w.tax.calculator;

import com.b2w.tax.model.ImpostoCompraOrms;
import com.b2w.tax.service.util.TaxUtil;

public class ImpostosCompraOrmsWrapper {

	private ImpostoCompraOrms impostoCompra;
	
	public ImpostosCompraOrmsWrapper(ImpostoCompraOrms impostoCompra) {
		this.impostoCompra = impostoCompra;
	}
	
	public void calculaIcmsFinal(){
		if(null != impostoCompra.getAliqIcms()){
			Float icmsFim = TaxUtil.multiplicaComArredondamento(impostoCompra.getAliqIcms(), impostoCompra.getBaseIcms());
			impostoCompra.setAliqIcmsFinal(TaxUtil.divideComArredondamento(icmsFim, 100f));
			
		}
		if(null != impostoCompra.getAliqIcmsst()){
			Float icmsStFim = TaxUtil.multiplicaComArredondamento(impostoCompra.getAliqIcmsst(), impostoCompra.getBaseIcmsst());
			impostoCompra.setAliqIcmsstFinal(TaxUtil.divideComArredondamento(icmsStFim, 100f));
		}
	}
	
	public void convertNullToZero(){
		if(null == impostoCompra.getAliqCofins()){
			impostoCompra.setAliqCofins(new Float(0));
		}
		if(null == impostoCompra.getAliqIcms()){
			impostoCompra.setAliqIcms(new Float(0));
		}
		if(null == impostoCompra.getAliqIcmsst()){
			impostoCompra.setAliqIcmsst(new Float(0));
		}
		if(null == impostoCompra.getAliqIpi()){
			impostoCompra.setAliqIpi(new Float(0));
		}
		if(null == impostoCompra.getAliqPis()){
			impostoCompra.setAliqPis(new Float(0));
		}
		if(null == impostoCompra.getIva()){
			impostoCompra.setIva(new Float(0));
		}
	}
	
	public void convertBaseReduzidas(){
		if(null == impostoCompra.getBaseIcms()){
			impostoCompra.setBaseIcms(new Float(100));
		}
		if(null == impostoCompra.getBaseIcmsst()){
			impostoCompra.setBaseIcmsst(new Float(100));
		}
		if(null == impostoCompra.getBaseIpi()){
			impostoCompra.setBaseIpi(new Float(100));
		}
	}
}
