package com.b2w.tax.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.b2w.tax.dao.FilialRegimeEspecialRepository;
import com.b2w.tax.model.database.FilialRegimeEspecial;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.b2w.tax.dao.ItemRepository;
import com.b2w.tax.exception.TaxException;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.Icms;
import com.b2w.tax.model.database.IcmsEstados;
import com.b2w.tax.model.database.IcmsNbmEstado;
import com.b2w.tax.model.database.Item;
import com.b2w.tax.service.util.TaxUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TaxUtil.class })
public class TaxServiceTest {

	@Mock
	private IcmsService icmsService;

	@Mock
	private ItemRepository itemRepository;

	@Mock
	private FilialRegimeEspecialRepository filialRegimeEspecialRepository;

	@InjectMocks
	private TaxService service;

	@Before
	public void setUp() {
		PowerMockito.mockStatic(TaxUtil.class);
	}

	@Test
	public void checkHasPpb_returnFalseIfICNENotFound() throws TaxException {
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(null);

		boolean checkHasPpb = service.checkHasPpb(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, only()).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertFalse(checkHasPpb);
	}

	@Test
	public void checkHasPpb_returnTrueIfIcneInForcaBaseReduzidaIsEqualTo_S_Letter() throws TaxException {
		IcmsNbmEstado icne = new IcmsNbmEstado();
		icne.setIcneInForcaBaseReduzida("S");

		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(icne);

		boolean checkHasPpb = service.checkHasPpb(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, only()).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(checkHasPpb);
	}

	@Test
	public void findBaseReduzidaPercent_returnBaseReduzidaByNbmEstadoIfItWasFound() {
		when(icmsService.findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(30.0f);

		Float result = service.findBaseReduzidaPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, only()).findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == (100.0 - 30.0));
	}

	@Test
	public void findBaseReduzidaPercent_ifBaseReduzidaNotFoundTryToUseIcmsByIcne() {
		IcmsNbmEstado icmsNbmEstado = new IcmsNbmEstado();
		icmsNbmEstado.setIcnePercRedBase(13.0F);

		when(icmsService.findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(icmsNbmEstado);
		when(TaxUtil.isAtivoAndHasRedBase(icmsNbmEstado)).thenReturn(true);

		Float result = service.findBaseReduzidaPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == (100.0 - 13.0F));
	}

	@Test
	public void findBaseReduzidaPercent_ifBaseReduzidaNotFoundAndInactivatedOrHasntRedBase() {
		when(icmsService.findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(new IcmsNbmEstado());
		when(TaxUtil.isAtivoAndHasRedBase(new IcmsNbmEstado())).thenReturn(false);

		Float result = service.findBaseReduzidaPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == 100.0);
	}

	@Test
	public void findBaseReduzidaPercent_ifBaseReduzidaNotFoundToTotalResultValueReturn_100_percent() {
		when(icmsService.findBaseReduzidaByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);

		Float result = service.findBaseReduzidaPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == 100.0);
	}

	@Test(expected = TaxNotFoundException.class)
	public void findIcmsPercent_ifIcmsByNbmAndUfNotFound() throws TaxNotFoundException, TaxException {
		when(icmsService.findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(null);
		when(icmsService.findIcmsEstadosByUfs("RJ", Arrays.asList(new String[] { "SP" }))).thenReturn(null);

		service.findIcmsPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findIcmsEstadosByUfs("RJ", Arrays.asList(new String[] { "SP" }));

	}

	@Test
	public void findIcmsPercent_foundIcmsPercentByNbmAndUf() throws TaxException {
		when(icmsService.findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(10.5F);

		Float result = service.findIcmsPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == 10.5F);
	}

	@Test
	public void findIcmsPercent_foundIcmsNbmEstadoFromIcneActivated() throws TaxException

	{
		IcmsNbmEstado icmsNbmEstado = new IcmsNbmEstado();
		icmsNbmEstado.setIcnePercIcms(10.0F);

		when(icmsService.findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(icmsNbmEstado);
		when(TaxUtil.isAtivoAndHasIcms(icmsNbmEstado)).thenReturn(true);

		Float result = service.findIcmsPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));

		Assert.assertTrue(result == 10.0F);
	}

	@Test
	public void findIcmsPercent_foundIcmsNbmEstadoFromIcneInactivated() throws TaxException {
		IcmsNbmEstado icmsNbmEstado = new IcmsNbmEstado();
		icmsNbmEstado.setIcnePercIcms(12.0F);
		IcmsEstados icmsEstados = new IcmsEstados();
		icmsEstados.setIcmePeIcms(25.0F);

		when(icmsService.findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(icmsNbmEstado);
		when(TaxUtil.isAtivoAndHasIcms(icmsNbmEstado)).thenReturn(false);
		when(icmsService.findIcmsEstadosByUfs("RJ", Arrays.asList(new String[] { "SP" })))
				.thenReturn(Arrays.asList(new IcmsEstados[] { icmsEstados }));

		Float result = service.findIcmsPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));

		verify(icmsService, times(1)).findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService, times(1)).findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }));
		verify(icmsService).findIcmsEstadosByUfs("RJ", Arrays.asList(new String[] { "SP" }));

		Assert.assertTrue(result == 25.0F);
	}

	@Test(expected = TaxNotFoundException.class)
	public void findIcmsPercent_foundIcmsNbmEstadoFromIcneInactivatedAndWithotIcmsEstados() throws TaxException {
		IcmsNbmEstado icmsNbmEstado = new IcmsNbmEstado();
		icmsNbmEstado.setIcnePercIcms(12.0F);

		when(icmsService.findIcmsOnNbmEstadosByNbmAndUf(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" })))
				.thenReturn(null);
		when(icmsService.findIcmsNbmEstadoFromIcne(0L, 5, TaxUtil.ID_CIA, "RJ", "SP",
				Arrays.asList(new String[] { "S" }))).thenReturn(icmsNbmEstado);
		when(TaxUtil.isAtivoAndHasIcms(icmsNbmEstado)).thenReturn(false);
		when(icmsService.findIcmsEstadosByUfs("RJ", Arrays.asList(new String[] { "SP" }))).thenReturn(null);

		service.findIcmsPercent(0L, 5, "RJ", "SP", Arrays.asList(new String[] { "S" }));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = TaxException.class)
	public void findNbm_throwExceptionIfItemNotFound() {
		BigInteger invalidSku = new BigInteger("99999");
		when(itemRepository.findByItemIdItem(invalidSku)).thenThrow(TaxException.class);

		itemRepository.findByItemIdItem(invalidSku);
	}

	@Test(expected = TaxException.class)
	public void findNbm_throwExceptionIfItemFoundButDontHaveIdNbm() throws TaxException {
		com.b2w.tax.model.database.Item item = new Item();
		when(itemRepository.findByItemIdItem(new BigInteger("666"))).thenReturn(item);

		service.findNbm("666");
	}

	//TODO uncomment this tests after removing mock method for filialRegimeEspecial
	/*@Test
	public void findFilialRegimeEspecial_assertMapCorrect(){

		FilialRegimeEspecial filialRegimeEspecial = mock(FilialRegimeEspecial.class);
		List<FilialRegimeEspecial> filialRegimeEspecials = new ArrayList<FilialRegimeEspecial>();
		filialRegimeEspecials.add(filialRegimeEspecial);
		when(filialRegimeEspecial.getFreeInRegime()).thenReturn("S");
		when(filialRegimeEspecial.getIdFilial()).thenReturn(50);

		when(filialRegimeEspecialRepository.findByIdCia(anyInt())).thenReturn(filialRegimeEspecials);

		Map<Integer, Boolean> result = service.findRegimeEspecialFiliais(1);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.get(50));

		when(filialRegimeEspecial.getFreeInRegime()).thenReturn("N");
		result = service.findRegimeEspecialFiliais(1);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertFalse(result.get(50));

	}

	@Test
	public void findFilialRegimeEspecial_resultNull(){

		when(filialRegimeEspecialRepository.findByIdCia(anyInt())).thenReturn(null);

		Map<Integer, Boolean> result = service.findRegimeEspecialFiliais(1);
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());

		FilialRegimeEspecial filialRegimeEspecial = mock(FilialRegimeEspecial.class);
		List<FilialRegimeEspecial> filialRegimeEspecials = new ArrayList<FilialRegimeEspecial>();
		filialRegimeEspecials.add(filialRegimeEspecial);
		when(filialRegimeEspecial.getFreeInRegime()).thenReturn(null);
		when(filialRegimeEspecial.getIdFilial()).thenReturn(50);

		when(filialRegimeEspecialRepository.findByIdCia(anyInt())).thenReturn(filialRegimeEspecials);

		result = service.findRegimeEspecialFiliais(1);
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertFalse(result.get(50));

	}*/


}
