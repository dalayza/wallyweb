package com.b2w.tax.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.b2w.tax.exception.TaxNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.b2w.tax.dao.NbmEstadoRepository;
import com.b2w.tax.exception.TaxServiceException;
import com.b2w.tax.model.database.NbmEstado;
import com.b2w.tax.service.util.TaxUtil;

@Service
public class NbmEstadoService {

	@Autowired
	private NbmEstadoRepository nbmEstadoRepository;

	@Cacheable(value="nbmEstadoCache", cacheManager="cacheManager")
	public NbmEstado findNbmEstado(Long nbm, Integer nbmSeq, String ufOrigem, String ufDestino,
			List<String> sentidos) throws TaxNotFoundException, TaxServiceException {
		List<NbmEstado> nbmEstados;
		if (null != nbmSeq) {
			nbmEstados = nbmEstadoRepository.findNbmEstadoByNbmAndUfsAndVigencia(nbm, nbmSeq, ufOrigem,
					Arrays.asList(ufDestino), new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		} else {
			nbmEstados = nbmEstadoRepository.findNbmEstadoByNbmAndSeqExternoUfsAndVigencia(
					TaxUtil.getNcmFromOrmsNcm(nbm), TaxUtil.getSeqExternalFromNcm(nbm), ufOrigem,
					Arrays.asList(ufDestino), new Timestamp(System.currentTimeMillis()), TaxUtil.ID_CIA, sentidos);
		}
		if (null == nbmEstados || nbmEstados.size() == 0) {
			throw new TaxNotFoundException("Não foram encontradas alíquotas para esta NCM, nesta origem e neste destino", nbm.toString(), ufOrigem, ufDestino);
		}else if(nbmEstados.size() > 1){
            throw new TaxServiceException("Não foi possível determinar uma alíquota para esta NCM, nesta origem e neste destino.", nbm.toString(), ufOrigem, ufDestino);
        }
		return nbmEstados.get(0);
	}

}
