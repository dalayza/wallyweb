package com.b2w.tax.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.b2w.tax.dao.NbmRepository;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.database.Nbm;
import com.b2w.tax.service.util.TaxUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TaxUtil.class })
public class NbmServiceTest {

	private NbmService service;

	@Mock
	private NbmRepository repository;

	@Before
	public void setUp() {
		this.service = new NbmService(repository);
		PowerMockito.mockStatic(TaxUtil.class);
	}

	@Test
	public void findNbm_byNcmSequential() throws TaxNotFoundException {
		when(repository.findNbmByNbmAndIdCia(0L, 5, TaxUtil.ID_CIA, todayTimestamp())).thenReturn(validNbm());

		Nbm nbm = service.findNbm(0L, 5);

		verify(repository, times(1)).findNbmByNbmAndIdCia(0L, 5, TaxUtil.ID_CIA, todayTimestamp());

		Assert.assertNotNull(nbm);
	}

	@Test
	public void findNbm_bySequentialExterno() throws TaxNotFoundException {
		long ncm = 0L;
		when(TaxUtil.getNcmFromOrmsNcm(ncm)).thenReturn(0L);
		when(TaxUtil.getSeqExternalFromNcm(ncm)).thenReturn("0003");
		when(repository.findNbmByNbmSeqExternoAndIdCia(0L, "0003", TaxUtil.ID_CIA, todayTimestamp()))
				.thenReturn(validNbm());

		Nbm nbm = service.findNbm(0L, null);

		verify(repository, times(1)).findNbmByNbmSeqExternoAndIdCia(0L, "0003", TaxUtil.ID_CIA, todayTimestamp());

		Assert.assertNotNull(nbm);
	}

	@Test
	public void findNbm_returnNullIfNeitherNcmSeqExistsNorTheNbmSeqExterno() throws TaxNotFoundException {
		when(repository.findNbmByNbmAndIdCia(0L, 5, TaxUtil.ID_CIA, todayTimestamp())).thenReturn(null);
		Nbm nbm = service.findNbm(0L, 5);
		Assert.assertNull(nbm);

		when(repository.findNbmByNbmSeqExternoAndIdCia(0L, "0003", TaxUtil.ID_CIA, todayTimestamp())).thenReturn(null);
		Nbm nbm2 = service.findNbm(0L, null);
		Assert.assertNull(nbm2);
	}

	private Timestamp todayTimestamp() {
		return new Timestamp(new DateTime().toLocalDate().toDate().getTime());
	}

	private Nbm validNbm() {
		return new Nbm();
	}

}
