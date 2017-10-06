package com.b2w.tax.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.b2w.tax.dao.NbmRepository;
import com.b2w.tax.exception.TaxNotFoundException;
import com.b2w.tax.model.database.Nbm;
import com.b2w.tax.service.util.TaxUtil;

@Service
public class NbmService {

    private NbmRepository nbmRepository;

    @Autowired
    public NbmService(NbmRepository repository) {
        this.nbmRepository = repository;
    }

    @Cacheable(value = "nbmCache", cacheManager = "cacheManager")
    public Nbm findNbm(Long ncm, Integer ncmSeq) throws TaxNotFoundException {

        if (ncm == null) {
            throw new TaxNotFoundException("NCM value couldn't be empty.");
        }

        Nbm nbmFound = null;
        if (ncmSeq != null) {
            nbmFound = nbmRepository.findNbmByNbmAndIdCia(ncm, ncmSeq, TaxUtil.ID_CIA,
                    new Timestamp(new DateTime().toLocalDate().toDate().getTime()));
        } else {
            nbmFound = nbmRepository.findNbmByNbmSeqExternoAndIdCia(TaxUtil.getNcmFromOrmsNcm(ncm),
                    TaxUtil.getSeqExternalFromNcm(ncm), TaxUtil.ID_CIA,
                    new Timestamp(new DateTime().toLocalDate().toDate().getTime()));
        }

        return nbmFound;
    }

    @Cacheable(value = "nbmCacheIpi", cacheManager = "cacheManager")
    public Optional<Nbm> findNbmForIpi(Long ncm, Integer ncmSeq) throws TaxNotFoundException {

        if (ncm == null)
            throw new TaxNotFoundException("NCM value couldn't be empty.");

        return nbmRepository.findNbmByNbmAndIdCia(ncm, ncmSeq, TaxUtil.ID_CIA);

    }

}
