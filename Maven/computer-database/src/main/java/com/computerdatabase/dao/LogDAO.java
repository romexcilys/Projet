package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.computerdatabase.domain.Logs;

public enum LogDAO {

	INSTANCE;

	public void logOperation(Logs log) throws SQLException {
		String query = null;
		
		if(log.getIdComputer() == -1)
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), null);";
		else
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), ?);";
			
		
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, log.getOperation());
		ps.setString(2, log.getName());
		
		if(log.getIdComputer() != -1)
			ps.setInt(3, log.getIdComputer());
		
		ps.execute();

	}

}
