package com.computerdatabase.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Logs;

@Component
public class LogDAO {
	
	@Autowired
	private DataSource connectionPool;
	
	private JdbcTemplate jdbcTemplate;

	public void create(Logs log) throws SQLException {
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		if(log.getIdComputer() == -1)
			this.jdbcTemplate.update("INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), null)", new Object[] {log.getOperation(), log.getName()});
		else
			this.jdbcTemplate.update("INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), ?)", new Object[] {log.getOperation(), log.getName(), new Long(log.getIdComputer()) });
		
	}

}
