package com.b2w.tax.service;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.b2w.tax.dao.IcmsEstadosRepository;
import com.b2w.tax.dao.IcmsNbmEstadoRepository;
import com.b2w.tax.dao.NbmEstadoRepository;
import com.b2w.tax.exception.TaxServiceException;
import com.b2w.tax.service.util.TaxUtil;

@RunWith(MockitoJUnitRunner.class)
public class IcmsServiceTest {

	private IcmsService service;

	@Mock
	private IcmsNbmEstadoRepository icmsNbmEstadoRepository;
	@Mock
	private NbmEstadoRepository nbmEstadoRepository;
	@Mock
	private IcmsEstadosRepository icmsEstadosRepository;
	@Mock
	private DifalService difalService;

	@Before
	public void setUp() {
		this.service = new IcmsService(icmsNbmEstadoRepository, nbmEstadoRepository, icmsEstadosRepository,
				difalService);
	}

	@Test
	public void findIcmsNbmEstadoFromIcne_FromUmbrellaWhenTryToGetByNcmSeq() throws TaxServiceException {

		service.findIcmsNbmEstadoFromIcne(82059000l, 3, TaxUtil.ID_CIA, "AM", "SC", TaxUtil.sentidoEntrada);

		verify(icmsNbmEstadoRepository, only()).findIcmsNbmEstadoByNbmAndUf(82059000l, 3, "AM", "SC", TaxUtil.ID_CIA,
				TaxUtil.sentidoEntrada, todayTimestamp(), paginationFirstPage());
	}

	@Test
	public void findIcmsNbmEstadoFromIcne_FromORMSWhenTryToGetByNcmCodeOnlyWithOwnExternalSeqOsConcatenatedOnNcmCode()
			throws TaxServiceException {
		Long ncm = 820590000055L;

		service.findIcmsNbmEstadoFromIcne(ncm, null, TaxUtil.ID_CIA, "AM", "SC", TaxUtil.sentidoEntrada);

		verify(icmsNbmEstadoRepository, only()).findIcmsNbmEstadoByNbmAndUfSeqExterno(TaxUtil.getNcmFromOrmsNcm(ncm),
				TaxUtil.getSeqExternalFromNcm(ncm), "AM", "SC", TaxUtil.ID_CIA, TaxUtil.sentidoEntrada,
				todayTimestamp(), paginationFirstPage());
	}


	private Timestamp todayTimestamp() {
		return new Timestamp(new DateTime().toLocalDate().toDate().getTime());
	}

	private PageRequest paginationFirstPage() {
		return new PageRequest(0, 1);
	}

}
