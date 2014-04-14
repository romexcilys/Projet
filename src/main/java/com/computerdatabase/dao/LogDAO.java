package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Logs;

@Component
public class LogDAO {
	
	@Autowired
	private DataSource connectionPool;
	

	public void logOperation(Logs log) throws SQLException {
		String query = null;
		
		if(log.getIdComputer() == -1)
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), null);";
		else
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), ?);";
			
		Connection connection = DataSourceUtils.getConnection(connectionPool);

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, log.getOperation());
		ps.setString(2, log.getName());
		
		if(log.getIdComputer() != -1)
			ps.setInt(3, log.getIdComputer());
		
		ps.execute();

	}

}
