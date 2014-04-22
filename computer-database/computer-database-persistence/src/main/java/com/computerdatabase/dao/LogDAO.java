package com.computerdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Logs;

@Component
public class LogDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Logs log) {
		log.setDate(DateTime.now());
		entityManager.persist(log);
	}

}
