package com.computerdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
			
		//entityManager.persist(log);
		if(log.getIdComputer() == -1)
			this.jdbcTemplate.update("INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), null)", new Object[] {log.getOperation(), log.getName()});
		else
			this.jdbcTemplate.update("INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), ?)", new Object[] {log.getOperation(), log.getName(), new Long(log.getIdComputer()) });
		
	}

}
