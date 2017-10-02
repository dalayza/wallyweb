package com.b2w.tax.calculator;

import com.b2w.tax.model.Icms;
import com.b2w.tax.model.ImpostosCompra;
import com.b2w.tax.model.Iva;
import com.b2w.tax.service.util.TaxUtil;

public class ImpostosCompraWrapper {

	private ImpostosCompra impostosCompra;

	public ImpostosCompraWrapper(ImpostosCompra impostosCompra) {
		this.impostosCompra = impostosCompra;
	}

	public void calculaValores() {
		if (null != impostosCompra.getValorBase()) {
			if (null != impostosCompra.getCofinsPercent()) {
				impostosCompra.setCofins(TaxUtil.multiplicaComArredondamento(impostosCompra.getCofinsPercent() / 100,
						impostosCompra.getValorBase()));
			}
			if (null != impostosCompra.getPisPercent()) {
				impostosCompra.setPis(TaxUtil.multiplicaComArredondamento(impostosCompra.getPisPercent() / 100,
						impostosCompra.getValorBase()));
			}
			for (Iva iva : impostosCompra.getIvas()) {
				iva.setValorBase(impostosCompra.getValorBase());
				if (null != iva.getIvaPercent()) {
					iva.setIva(TaxUtil.multiplicaComArredondamento(iva.getIvaPercent() / 100, iva.getValorBase()));
				}
			}
		}
	}

}
