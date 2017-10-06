package com.b2w.tax.dao;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.b2w.tax.model.database.Nbm;

public interface NbmRepository extends JpaRepository<Nbm, Integer>, JpaSpecificationExecutor<Nbm> {

    @Cacheable("taxes")
    @Query("FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND nbmSituacao = 'A'")
    public Optional<Nbm> findNbmByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia);

    @Cacheable("taxes")
    @Query("FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Nbm findNbmByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia, Timestamp today);

    @Cacheable("taxes")
    @Query("FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Nbm findNbmByNbmSeqExternoAndIdCia(Long nbm, String seqExterno, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercPisSai FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findPisSaiByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia, Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercPisSai FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findPisSaiByNbmSeqExternoAndIdCia(Long nbm, String seqExterno, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercCofinsSai FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND ?4 < nbmDtVigenciaFim AND nbmSituacao = 'A'")
    public Float findCofinsSaiByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercCofinsSai FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findCofinsSaiByNbmSeqExternoAndIdCia(Long nbm, String seqExterno, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercCofins FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findCofinsByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia, Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercCofins FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findCofinsByNbmAndIdCiaSeqExterno(Long nbm, String nbmSeqExterno, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercPis FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findPisByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia, Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercPis FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findPisByNbmAndIdCiaSeqExterno(Long nbm, String nbmSeq, Integer idCia,
            Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercIpi FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findIpiByNbmAndIdCia(Long nbm, Integer nbmSeq, Integer idCia, Timestamp today);

    @Cacheable("taxes")
    @Query("SELECT nbmPercIpi FROM Nbm where nbmIdNbm = ?1 AND ((?2 is null AND nbmSeqExterno is null) OR nbmSeqExterno = ?2) AND nbmIdCia = ?3 AND (nbmDtVigenciaIni is null OR ?4 >= nbmDtVigenciaIni) AND (nbmDtVigenciaFim is null OR ?4 < nbmDtVigenciaFim) AND nbmSituacao = 'A'")
    public Float findIpiByNbmAndIdCiaSeqExterno(Long nbm, String seqExterno, Integer idCia,
            Timestamp today);

    /*
     * @Query("SELECT nbmPercMargemLucro FROM Nbm where nbmIdNbm = ?1 AND nbmSeq = ?2 AND nbmIdCia = ?3"
     * ) public Float findIvaByNbmAndIdCia(Integer nbm, Integer nbmSeq, Integer idCia);
     */

}
