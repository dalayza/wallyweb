package com.b2w.tax.dao;

import com.b2w.tax.model.database.FilialRegimeEspecial;
import com.b2w.tax.model.database.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;
import java.util.List;

public interface FilialRegimeEspecialRepository extends JpaRepository<FilialRegimeEspecial, Integer>, JpaSpecificationExecutor<Integer> {

	public List<FilialRegimeEspecial> findByIdCia(Integer idCia);

}
