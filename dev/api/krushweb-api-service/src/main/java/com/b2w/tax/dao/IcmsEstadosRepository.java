package com.b2w.tax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.b2w.tax.model.database.IcmsEstados;
import com.b2w.tax.model.database.IcmsEstadosId;

public interface IcmsEstadosRepository extends JpaRepository<IcmsEstados, IcmsEstadosId>, JpaSpecificationExecutor<IcmsEstados> {

	@Query("from IcmsEstados where icmeIdEstadoDe = ?1 and icmeIdEstadoPara in ?2")
	public List<IcmsEstados> findByIcmeIdEstadoDeAndIcmeIdEstadoPara(String icmeIdEstadoDe, List<String> icmeIdEstadoPara);
	
	@Query("from IcmsEstados where icmeIdEstadoDe = icmeIdEstadoPara")
	public List<IcmsEstados> findIcmsEstadosInternas();
	
	@Query("select icmePeIcms from IcmsEstados where icmeIdEstadoDe = ?1 and icmeIdEstadoPara = ?2")
	public Float findIcmsByEstadoOrigemAndDestino(String icmeIdEstadoDe, String icmeIdEstadoPara);
}
