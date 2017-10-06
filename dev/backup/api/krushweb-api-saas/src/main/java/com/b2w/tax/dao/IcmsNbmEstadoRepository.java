package com.b2w.tax.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.b2w.tax.model.database.IcmsNbmEstado;

public interface IcmsNbmEstadoRepository
		extends JpaRepository<IcmsNbmEstado, Integer>, JpaSpecificationExecutor<IcmsNbmEstado> {

	@Cacheable("taxes")
	@Query("from IcmsNbmEstado where icneIdNbm = ?1 and icneSeqNbm = ?2 and icneIdEstadoDe = ?3 and icneIdEstadoPara = ?4 and icneIdCia = ?5 and icneSentido in ?6 and ?7 >= icneVigenciaIni and ?7 < icneVigenciaFim order by icneVigenciaIni desc")
	public List<IcmsNbmEstado> findIcmsNbmEstadoByNbmAndUf(Long nbm, Integer seqNbm, String ufOrigem, String ufDestino,
			Integer idCia, List<String> sentido, Timestamp today, Pageable pageable);

	@Query("from IcmsNbmEstado where icneIdNbm = ?1 and icneSeqNbm = ?2 and icneIdEstadoDe = icneIdEstadoPara and icneIdCia = ?3 and icneSentido in ?4 and ?5 >= icneVigenciaIni and ?5 < icneVigenciaFim order by icneVigenciaIni desc")
	public List<IcmsNbmEstado> findIcneInternas(Long nbm, Integer seqNbm, Integer idCia, List<String> sentido,
			Timestamp today);

	@Cacheable("taxes")
	@Query("select icne from IcmsNbmEstado icne join icne.nbm n where icne.icneIdNbm = ?1 and icne.icneIdEstadoDe = ?3 and icne.icneIdEstadoPara = ?4 and icne.nbm.nbmIdCia = ?5 and icne.icneSentido in ?6 and ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) and ?7 >= icne.icneVigenciaIni and ?7 < icne.icneVigenciaFim and icne.icneSituacao = 'A' order by icne.icneVigenciaIni desc")
	public List<IcmsNbmEstado> findIcmsNbmEstadoByNbmAndUfSeqExterno(Long nbm, String seqExterno, String ufOrigem,
			String ufDestino, Integer idCia, List<String> sentido, Timestamp today, Pageable pageable);

	@Cacheable("taxes")
	@Query("from IcmsNbmEstado where icneIdNbm = ?1 and icneSeqNbm = ?2 and icneIdEstadoDe = ?3 and icneIdEstadoPara in ?4 and icneIdCia = ?5 and icneSentido in ?6 and ?7 >= icneVigenciaIni and ?7 < icneVigenciaFim and icneSituacao = 'A' order by icneVigenciaIni desc, icneIdEstadoPara asc")
	public List<IcmsNbmEstado> findIcmsNbmEstadosByNbmAndUfs(Long nbm, Integer seqNbm, String ufOrigem,
			List<String> ufDestino, Integer idCia, List<String> sentido, Timestamp today);

	@Cacheable("taxes")
	@Query("select icne from IcmsNbmEstado icne join icne.nbm n where icne.icneIdNbm = ?1 and icne.icneIdEstadoDe = ?3 and icne.icneIdEstadoPara in ?4 and icne.nbm.nbmIdCia = ?5 and icne.icneSentido in ?6 and ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) and ?7 >= icne.icneVigenciaIni and ?7 < icne.icneVigenciaFim and icne.icneSituacao = 'A' order by icne.icneVigenciaIni desc, icne.icneIdEstadoPara asc")
	public List<IcmsNbmEstado> findIcmsNbmEstadosByNbmAndUfsSeqExterno(Long nbm, String seqExterno, String ufOrigem,
			List<String> ufDestino, Integer idCia, List<String> sentido, Timestamp today);

	@Query("select icne from IcmsNbmEstado icne join icne.nbm n where icne.icneIdNbm = ?1 and icne.icneIdEstadoDe = icne.icneIdEstadoPara and icne.nbm.nbmIdCia = ?3 and icne.icneSentido in ?4 and ((?2 is null AND n.nbmSeqExterno is null) OR n.nbmSeqExterno = ?2) and ?5 >= icne.icneVigenciaIni and ?5 < icne.icneVigenciaFim and icne.icneSituacao = 'A' order by icne.icneVigenciaIni desc, icne.icneIdEstadoPara asc")
	public List<IcmsNbmEstado> findIcneInternas(Long nbm, String seqExterno, Integer idCia, List<String> sentido,
			Timestamp today);
}
