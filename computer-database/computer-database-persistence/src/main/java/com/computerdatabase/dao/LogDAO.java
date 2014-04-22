package com.computerdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Logs;

@Component
public class LogDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
	private EntityManager entityManager;

	public void create(Logs log){
		log.setDate(DateTime.now());
		entityManager.persist(log);
	}

}
