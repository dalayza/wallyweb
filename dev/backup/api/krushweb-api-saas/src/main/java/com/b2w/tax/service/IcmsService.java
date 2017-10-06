package com.b2w.tax.service;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.b2w.tax.dao.IcmsEstadosRepository;
import com.b2w.tax.dao.IcmsNbmEstadoRepository;
import com.b2w.tax.dao.NbmEstadoRepository;
import com.b2w.tax.model.database.DifalRule;
import com.b2w.tax.model.database.IcmsEstados;
import com.b2w.tax.model.database.IcmsNbmEstado;
import com.b2w.tax.model.database.NbmEstado;
import com.b2w.tax.service.util.TaxUtil;

@Service
public class IcmsService {

	private static Logger logger = Logger.getLogger(IcmsService.class);

	private IcmsNbmEstadoRepository icmsNbmEstadoRepository;

	private NbmEstadoRepository nbmEstadoRepository;

	private IcmsEstadosRepository icmsEstadosRepository;

	private DifalService difalService;

	@Autowired
	public IcmsService(IcmsNbmEstadoRepository icmsNbmEstadoRepository, NbmEstadoRepository nbmEstadoRepository,
			IcmsEstadosRepository icmsEstadosRepositor, DifalService difalService) {
		this.icmsNbmEstadoRepository = icmsNbmEstadoRepository;
		this.nbmEstadoRepository = nbmEstadoRepository;
		this.icmsEstadosRepository = icmsEstadosRepositor;
		this.difalService = difalService;
	}

	@Cacheable(value = "icmsNbmEstadoFromIcneCache", cacheManager = "cacheManager")
	public IcmsNbmEstado findIcmsNbmEstadoFromIcne(Long ncm, Integer ncmSeq, int idCia, String ufOrigem,
			String ufDestino, List<String> sentido) {

		IcmsNbmEstado icmsNbmEstado = null;
		List<IcmsNbmEstado> icmsNbmEstados = null;
		long today = new DateTime().toLocalDate().toDate().getTime();

		if (ncmSeq != null) {
			logger.info("Will find ICMS from ICNE NCM = " + ncm + " NCMSEQ = " + ncmSeq + " IDCIA = " + idCia
					+ " UFORIGEM = " + ufOrigem + " UFDESTINO = " + ufDestino);
			icmsNbmEstados = icmsNbmEstadoRepository.findIcmsNbmEstadoByNbmAndUf(ncm, ncmSeq, ufOrigem, ufDestino,
					idCia, sentido, new Timestamp(today), new PageRequest(0, 1));
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			logger.info("Will find ICMS from ICNE JOIN NBM NCM = " + ncm + " SEQ_EXTERNO = " + seqExterno + " IDCIA = "
					+ idCia + " UFORIGEM = " + ufOrigem + " UFDESTINO = " + ufDestino);
			icmsNbmEstados = icmsNbmEstadoRepository.findIcmsNbmEstadoByNbmAndUfSeqExterno(
					TaxUtil.getNcmFromOrmsNcm(ncm), seqExterno, ufOrigem, ufDestino, idCia, sentido,
					new Timestamp(today), new PageRequest(0, 1));
		}

		if (icmsNbmEstados != null && icmsNbmEstados.size() >= 1) {
			icmsNbmEstado = icmsNbmEstados.get(0);
		}

		return icmsNbmEstado;
	}

	@Cacheable(value = "icmsNbmEstadosFromIcneCache", cacheManager = "cacheManager")
	public List<IcmsNbmEstado> findIcmsNbmEstadosFromIcne(Long ncm, Integer ncmSeq, int idCia, String ufOrigem,
			List<String> ufDestino, List<String> sentido) {
		List<IcmsNbmEstado> icmsNbmEstados = null;

		if (ncmSeq != null) {
			logger.info("Will find ICMS from ICNE NCM = " + ncm + " NCMSEQ = " + ncmSeq + " IDCIA = " + idCia
					+ " UFORIGEM = " + ufOrigem + " UFDESTINO = " + ufDestino);
			icmsNbmEstados = icmsNbmEstadoRepository.findIcmsNbmEstadosByNbmAndUfs(ncm, ncmSeq, ufOrigem, ufDestino,
					idCia, sentido, new Timestamp(DateTime.now().getMillis()));
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			logger.info("Will find ICMS from ICNE JOIN NBM NCM = " + ncm + " SEQ_EXTERNO = " + seqExterno + " IDCIA = "
					+ idCia + " UFORIGEM = " + ufOrigem + " UFDESTINO = " + ufDestino);
			icmsNbmEstados = icmsNbmEstadoRepository.findIcmsNbmEstadosByNbmAndUfsSeqExterno(
					TaxUtil.getNcmFromOrmsNcm(ncm), seqExterno, ufOrigem, ufDestino, idCia, sentido,
					new Timestamp(DateTime.now().getMillis()));
		}

		if (icmsNbmEstados != null) {
			// vai considerar somente os 26 primeiros registros
			Iterator<IcmsNbmEstado> it = icmsNbmEstados.iterator();
			limitListToNumber(it, 27);
		}
		return icmsNbmEstados;
	}

	private void limitListToNumber(Iterator<?> it, int limit) {
		int i = 0;
		while (it.hasNext()) {
			it.next();
			i++;
			if (i > limit) {
				it.remove();
			}
		}
	}

	@Cacheable(value = "icneInternas", cacheManager = "cacheManager")
	public List<IcmsNbmEstado> findIcneInternas(Long ncm, Integer ncmSeq, int idCia, List<String> sentido) {
		List<IcmsNbmEstado> result = null;
		if (null != ncmSeq) {
			result = icmsNbmEstadoRepository.findIcneInternas(ncm, ncmSeq, idCia, sentido,
					new Timestamp(System.currentTimeMillis()));
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			result = icmsNbmEstadoRepository.findIcneInternas(TaxUtil.getNcmFromOrmsNcm(ncm), seqExterno, idCia,
					sentido, new Timestamp(System.currentTimeMillis()));
		}
		if (null != result) {
			limitListToNumber(result.iterator(), 27);
		}
		return result;
	}

	@Cacheable(value = "nbmEstadoInternas", cacheManager = "cacheManager")
	public List<NbmEstado> findNbmEstadoInternas(Long ncm, Integer ncmSeq, List<String> sentidos) {
		if (ncmSeq != null) {
			return nbmEstadoRepository.findNbmEstadoInternas(ncm, ncmSeq, new Timestamp(System.currentTimeMillis()),
					TaxUtil.ID_CIA, sentidos);

		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			return nbmEstadoRepository.findNbmEstadoInternas(TaxUtil.getNcmFromOrmsNcm(ncm), seqExterno,
					new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		}
	}

	@Cacheable(value = "icmsByNbmAndSeqExternoUfAndVigenciaCache", cacheManager = "cacheManager")
	public Float findIcmsOnNbmEstadosByNbmAndUf(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
			List<String> sentidos) {
		if (null != ncmSeq) {
			return nbmEstadoRepository.findIcmsByNbmAndUfAndVigencia(ncm, ncmSeq, ufOrigem, ufDestino,
					new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			return nbmEstadoRepository.findIcmsByNbmAndSeqExternoUfAndVigencia(TaxUtil.getNcmFromOrmsNcm(ncm),
					seqExterno, ufOrigem, ufDestino, new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
					sentidos);
		}
	}

	@Cacheable(value = "icmsStByNbmAndSeqExternoUfAndVigenciaCache", cacheManager = "cacheManager")
	public Float findIcmsStOnNbmEstadosByNbmAndUf(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
			List<String> sentidos) {
		if (null != ncmSeq) {
			return nbmEstadoRepository.findIcmsStByNbmAndUfAndVigencia(ncm, ncmSeq, ufOrigem, ufDestino,
					new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			return nbmEstadoRepository.findIcmsStByNbmAndSeqExternoUfAndVigencia(TaxUtil.getNcmFromOrmsNcm(ncm),
					seqExterno, ufOrigem, ufDestino, new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
					sentidos);
		}
	}

	public Float findBaseReduzidaByNbmAndUf(Long ncm, Integer ncmSeq, String ufOrigem, String ufDestino,
			List<String> sentidos) {
		if (null != ncmSeq) {
			return nbmEstadoRepository.findRedBaseByNbmAndUfAndVigencia(ncm, ncmSeq, ufOrigem, ufDestino,
					new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			return nbmEstadoRepository.findRedBaseByNbmAndSeqExternoUfAndVigencia(TaxUtil.getNcmFromOrmsNcm(ncm),
					seqExterno, ufOrigem, ufDestino, new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
					sentidos);
		}
	}

	public List<NbmEstado> findIcmsOnNbmEstadosByNbmAndUfs(Long ncm, Integer ncmSeq, String ufOrigem,
			List<String> ufDestino, List<String> sentidos) {
		if (null != ncmSeq) {
			return nbmEstadoRepository.findNbmEstadoByNbmAndUfsAndVigencia(ncm, ncmSeq, ufOrigem, ufDestino,
					new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		} else {
			String seqExterno = TaxUtil.getSeqExternalFromNcm(ncm);
			return nbmEstadoRepository.findNbmEstadoByNbmAndSeqExternoUfsAndVigencia(TaxUtil.getNcmFromOrmsNcm(ncm),
					seqExterno, ufOrigem, ufDestino, new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA,
					sentidos);
		}
	}

	@Cacheable(value = "icmsEstadosInternas", cacheManager = "cacheManager")
	public List<IcmsEstados> findIcmsEstadosInternas() {
		return icmsEstadosRepository.findIcmsEstadosInternas();
	}

	@Cacheable(value = "icmsEstadosByUfs", cacheManager = "cacheManager")
	public List<IcmsEstados> findIcmsEstadosByUfs(String ufOrigem, List<String> ufsDestino) {
		return icmsEstadosRepository.findByIcmeIdEstadoDeAndIcmeIdEstadoPara(ufOrigem, ufsDestino);
	}

	public Float findIcmsOnIcmsEstadosByUf(String ufOrigem, String ufDestino) {
		return icmsEstadosRepository.findIcmsByEstadoOrigemAndDestino(ufOrigem, ufDestino);
	}

	public Float calculateDifalOrigem(Float icmsVenda, Float icmsInterestadual) {
		Float difal = icmsVenda - icmsInterestadual;
		DifalRule difalRule = difalService.getCurrentDifalRuleWithCache();
		Float percentOrigem = 100f - difalRule.getPercentDestino();
		return difal * (percentOrigem / 100f);
	}

	public Float calculateDifalDestino(Float icmsVenda, Float icmsInterestadual) {
		Float difal = icmsVenda - icmsInterestadual;
		DifalRule difalRule = difalService.getCurrentDifalRuleWithCache();
		return difal * (difalRule.getPercentDestino() / 100f);
	}
}
