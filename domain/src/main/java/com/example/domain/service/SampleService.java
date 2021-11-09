package com.example.domain.service;

import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.example.domain.db.SampleEntity;

@Dependent
public class SampleService {

	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@PersistenceContext(unitName = "SampleUnit")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public void persist() {
		logger.info("サービスクラスの処理実施");
		SampleEntity entity = new SampleEntity();
		entity.setValue(String.valueOf(new Random().nextLong()));
		entity.setId(new Random().nextInt());
		em.persist(entity);
		logger.info("サービスクラスの処理終了");
	}
	
}
