package com.b2w.tax.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.b2w.tax.exception.TaxException;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.NomenclaturaBrasileiraMercadorias;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ApplicationTest.class })
public class TaxVendaCofinsTest {

	@Autowired
	private TaxVendaService service;

	@Autowired
	private TaxService taxService;

	@Test
	public void findCofinsPercent_returnCofinsIfNbmIsValid() throws NumberFormatException, TaxException {
		NomenclaturaBrasileiraMercadorias nbm = taxService.findNbm("5477262");
		Float cofins = service.findCofinsPercent(nbm.getNbm(), nbm.getSequentialUmbrella());
		Assert.assertTrue(cofins != null);
	}

	@Test(expected = TaxNotFoundException.class)
	public void findCofinsPercent_usingNullValueToNBM() throws NumberFormatException, TaxException {
		service.findCofinsPercent(null, null);
	}

}
