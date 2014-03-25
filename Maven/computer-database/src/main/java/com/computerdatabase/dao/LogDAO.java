package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.computerdatabase.domain.Logs;

public class LogDAO {

	private static LogDAO logDAO = null;

	public static LogDAO getInstance() {
		if (logDAO == null)
			logDAO = new LogDAO();

		return logDAO;
	}

	public void logOperation(Logs log) throws SQLException {
		String query = null;
		
		if(log.getIdComputer() == -1)
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), null);";
		else
			query = "INSERT INTO log (operation, name, date, idComputer) VALUES (?, ?, NOW(), ?);";
			
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, log.getOperation());
		ps.setString(2, log.getName());
		
		if(log.getIdComputer() != -1)
			ps.setInt(3, log.getIdComputer());
		
		ps.execute();

	}

}
