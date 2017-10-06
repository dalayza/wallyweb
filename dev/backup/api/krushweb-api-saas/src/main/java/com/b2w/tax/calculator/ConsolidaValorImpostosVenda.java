package com.b2w.tax.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.b2w.tax.model.BaseReduzida;
import com.b2w.tax.model.Icms;
import com.b2w.tax.model.ImpostoVenda;
import com.b2w.tax.model.Impostos;
import com.b2w.tax.model.ImpostosVenda;
import com.b2w.tax.service.util.TaxUtil;

public class ConsolidaValorImpostosVenda {

	protected Impostos impostos;

	public ConsolidaValorImpostosVenda(Impostos impostos) {
		this.impostos = impostos;
	}

	public void calcular() {
		if (null != impostos.getValorBase()) {
			if (null != impostos.getCofinsPercent()) {
				impostos.setCofins(TaxUtil.multiplicaComArredondamento(impostos.getCofinsPercent() / 100,
						impostos.getValorBase()));
			}
			if (null != impostos.getPisPercent()) {
				impostos.setPis(
						TaxUtil.multiplicaComArredondamento(impostos.getPisPercent() / 100, impostos.getValorBase()));
			}

			if (impostos instanceof ImpostosVenda) {
				ImpostosVenda impostosVenda = (ImpostosVenda) impostos;
				Map<String, Float> baseReduzidasPercentMap = new HashMap<String, Float>();
				for (BaseReduzida baseReduzida : impostosVenda.getBaseReduzidas()) {
					baseReduzida.setValorBase(impostosVenda.getValorBase());
					if (null != baseReduzida.getBaseReduzidaPercent()) {
						baseReduzida.setBaseReduzida(TaxUtil.multiplicaComArredondamento(
								baseReduzida.getBaseReduzidaPercent() / 100, baseReduzida.getValorBase()));
						baseReduzidasPercentMap.put(baseReduzida.getUfOrigem() + baseReduzida.getUfDestino(),
								baseReduzida.getBaseReduzidaPercent());
					}
				}
				List<Icms> icmsFinais = new ArrayList<Icms>();
				for (Icms icms : impostosVenda.getIcmsVendas()) {

					if (icms.getValorBase() == null) {
						icms.setValorBase(impostosVenda.getValorBase());
					}

					if(null != icms.getIcmsPercent() && 0 == icms.getIcmsPercent()){
						icms.setValorBase(0f);
					}

					if (null != icms.getIcmsPercent()) {
						icms.setIcms(
								TaxUtil.multiplicaComArredondamento(icms.getIcmsPercent() / 100, icms.getValorBase()));
						Icms icmsFinal = new Icms();
						icmsFinal.setUfOrigem(icms.getUfOrigem());
						icmsFinal.setUfDestino(icms.getUfDestino());
						icmsFinal.setValorBase(icms.getValorBase());
						icmsFinal.setTipoImposto(icms.getTipoImposto());
						icmsFinal.setIcmsPercent((icms.getIcmsPercent()
								* baseReduzidasPercentMap.get(icms.getUfOrigem() + icms.getUfDestino())) / 100);
						icmsFinal.setIcms(TaxUtil.multiplicaComArredondamento(icmsFinal.getIcmsPercent() / 100,
								icms.getValorBase()));
						icmsFinais.add(icmsFinal);
					}
				}
				impostosVenda.setIcmsFinais(icmsFinais);
			} else if (impostos instanceof ImpostoVenda) {
				ImpostoVenda impostoVenda = (ImpostoVenda) impostos;
				if (null != impostoVenda.getBaseReduzida()
						&& null != impostoVenda.getBaseReduzida().getBaseReduzidaPercent()) {
					impostoVenda.getBaseReduzida().setValorBase(impostoVenda.getValorBase());
					impostoVenda.getBaseReduzida()
							.setBaseReduzida(TaxUtil.multiplicaComArredondamento(
									impostoVenda.getBaseReduzida().getBaseReduzidaPercent() / 100,
									impostoVenda.getBaseReduzida().getValorBase()));
				}
				if (null != impostoVenda.getIcmsVenda() && null != impostoVenda.getIcmsVenda().getIcmsPercent()) {
					impostoVenda.getIcmsVenda().setValorBase(impostoVenda.getValorBase());
					impostoVenda.getIcmsVenda()
							.setIcms(TaxUtil.multiplicaComArredondamento(
									impostoVenda.getIcmsVenda().getIcmsPercent() / 100,
									impostoVenda.getIcmsVenda().getValorBase()));
					Icms icmsFinal = new Icms();
					icmsFinal.setUfOrigem(impostoVenda.getIcmsVenda().getUfOrigem());
					icmsFinal.setUfDestino(impostoVenda.getIcmsVenda().getUfDestino());
					icmsFinal.setTipoImposto(impostoVenda.getIcmsVenda().getTipoImposto());
					icmsFinal.setValorBase(impostoVenda.getIcmsVenda().getValorBase());
					icmsFinal.setIcmsPercent((impostoVenda.getIcmsVenda().getIcmsPercent()
							* impostoVenda.getBaseReduzida().getBaseReduzidaPercent()) / 100);
					icmsFinal.setIcms(TaxUtil.multiplicaComArredondamento(icmsFinal.getIcmsPercent() / 100,
							icmsFinal.getValorBase()));
					impostoVenda.setIcmsFinal(icmsFinal);
				}
			}
		}
	}
}
