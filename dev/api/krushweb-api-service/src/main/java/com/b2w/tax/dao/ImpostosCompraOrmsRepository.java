package com.b2w.tax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.b2w.tax.model.database.ImpostoCompraDb;


public interface ImpostosCompraOrmsRepository extends JpaRepository<ImpostoCompraDb, Integer>, JpaSpecificationExecutor<ImpostoCompraDb>{
	
	@Query("from ImpostoCompraDb p where p.classificacao = ?1 and p.regiao = ?2 and p.utilizacao = ?3 and p.cfop = ?4 order by dataInicio desc")
	public List<ImpostoCompraDb> findImpostosCompraByUtilizacaoECfop(String ncm, String regiao, Integer utilizacao, Integer cfop);
	
}