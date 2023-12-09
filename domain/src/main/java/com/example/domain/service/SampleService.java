package com.example.domain.service;

import java.util.logging.Logger;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Dependent
public class SampleService {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	 @PersistenceContext(unitName = "SampleUnit")
	 private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void persist() {
	}
	
}
