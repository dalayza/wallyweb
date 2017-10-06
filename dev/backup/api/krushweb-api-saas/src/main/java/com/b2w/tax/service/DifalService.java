package com.b2w.tax.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.b2w.tax.dao.DifalRepository;
import com.b2w.tax.model.database.DifalRule;

@Service
public class DifalService {
	
	@Autowired
	private DifalRepository difalRepository;
	
	@Cacheable(value="difalCache", cacheManager = "cacheManager")
	public DifalRule getCurrentDifalRuleWithCache(){
		return difalRepository.findByVigencia(new Timestamp(System.currentTimeMillis()));
	}
	
	public DifalRule getCurrentDifalRule(){
		return difalRepository.findByVigencia(new Timestamp(System.currentTimeMillis()));
	}

}
