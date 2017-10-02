package com.b2w.tax.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.b2w.tax.model.database.NbmEstado;

public interface NbmEstadoRepository extends JpaRepository<NbmEstado, Integer>, JpaSpecificationExecutor<NbmEstado> {

	// TODO checar se deve usar >= <= na comparação de data vigência
	// @Cacheable("taxes")("icmssByNbmAndUfAndVigencia")
	@Query("SELECT nbmePercIcms FROM NbmEstado WHERE nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbmeIdEstadoDe = ?3 AND nbmeIdEstadoPara = ?4 AND ?5 >= nbmeDtVigencia  AND ?5 <= nbmeDtVigenciaFinal AND nbmeIdCia = ?6 AND nbmeSentido in ?7")
	public Float findIcmsByNbmAndUfAndVigencia(Long nbm, Integer nbmSeq, String ufOrigem, String ufDestino,
			Timestamp today, Integer idCia, List<String> sentidos);

	@Query("FROM NbmEstado WHERE nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbmeIdEstadoDe = nbmeIdEstadoPara AND ?3 >= nbmeDtVigencia  AND ?3 <= nbmeDtVigenciaFinal AND nbmeIdCia = ?4 AND nbmeSentido in ?5")
	public List<NbmEstado> findNbmEstadoInternas(Long nbm, Integer nbmSeq, Timestamp today, Integer idCia,
			List<String> sentidos);

	@Query("SELECT nbmePercSubstrib FROM NbmEstado WHERE nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbmeIdEstadoDe = ?3 AND nbmeIdEstadoPara = ?4 AND ?5 >= nbmeDtVigencia  AND ?5 <= nbmeDtVigenciaFinal AND nbmeIdCia = ?6 AND nbmeSentido in ?7")
	public Float findIcmsStByNbmAndUfAndVigencia(Long nbm, Integer nbmSeq, String ufOrigem, String ufDestino,
			Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbmePercRedIcms FROM NbmEstado WHERE nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbmeIdEstadoDe = ?3 AND nbmeIdEstadoPara = ?4 AND ?5 >= nbmeDtVigencia  AND ?5 <= nbmeDtVigenciaFinal AND nbmeIdCia = ?6 AND nbmeSentido in ?7")
	public Float findRedBaseByNbmAndUfAndVigencia(Long nbm, Integer nbmSeq, String ufOrigem, String ufDestino,
			Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbme.nbmePercIcms FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara = ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public Float findIcmsByNbmAndSeqExternoUfAndVigencia(Long nbm, String seqExterno, String ufOrigem, String ufDestino,
			Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbme.nbmePercMargemLucro FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara = ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public Float findIvaByNbmAndSeqExternoUf(Long nbm, String seqExterno, String ufOrigem, String ufDestino,
			Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbme.nbmePercMargemLucro FROM NbmEstado nbme WHERE nbme.nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara = ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public Float findIvaByNbmAndUf(Long nbm, Integer nbmSeq, String ufOrigem, String ufDestino, Timestamp today,
			Integer idCia, List<String> sentidos);

	@Query("SELECT nbme FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = nbme.nbmeIdEstadoPara AND ?3 >= nbme.nbmeDtVigencia  AND ?3 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?4 AND nbme.nbmeSentido in ?5")
	public List<NbmEstado> findNbmEstadoInternas(Long nbm, String seqExterno, Timestamp today, Integer idCia,
			List<String> sentidos);

	@Query("SELECT nbme.nbmePercSubstrib FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara = ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public Float findIcmsStByNbmAndSeqExternoUfAndVigencia(Long nbm, String seqExterno, String ufOrigem,
			String ufDestino, Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbme.nbmePercRedIcms FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara = ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public Float findRedBaseByNbmAndSeqExternoUfAndVigencia(Long nbm, String seqExterno, String ufOrigem,
			String ufDestino, Timestamp today, Integer idCia, List<String> sentidos);

	@Query("FROM NbmEstado WHERE nbmeIdNbm = ?1 AND nbmeSeqNbm = ?2 AND nbmeIdEstadoDe = ?3 AND nbmeIdEstadoPara in ?4 AND ?5 >= nbmeDtVigencia  AND ?5 <= nbmeDtVigenciaFinal AND nbmeIdCia = ?6 AND nbmeSentido in ?7")
	public List<NbmEstado> findNbmEstadoByNbmAndUfsAndVigencia(Long nbm, Integer nbmSeq, String ufOrigem,
			List<String> ufDestino, Timestamp today, Integer idCia, List<String> sentidos);

	@Query("SELECT nbme FROM NbmEstado nbme join nbme.nbm n WHERE nbme.nbmeIdNbm = ?1 AND ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) AND nbme.nbmeIdEstadoDe = ?3 AND nbme.nbmeIdEstadoPara in ?4 AND ?5 >= nbme.nbmeDtVigencia  AND ?5 <= nbme.nbmeDtVigenciaFinal  AND nbme.nbmeIdCia = ?6 AND nbme.nbmeSentido in ?7")
	public List<NbmEstado> findNbmEstadoByNbmAndSeqExternoUfsAndVigencia(Long nbm, String seqExterno, String ufOrigem,
			List<String> ufDestino, Timestamp today, Integer idCia, List<String> sentidos);
}
