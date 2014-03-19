package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDAO {

	private static LogDAO logDAO = null;

	public static LogDAO getInstance() {
		if (logDAO == null)
			logDAO = new LogDAO();

		return logDAO;
	}

	public void logOperation(String fonction) throws SQLException {
		String query = "INSERT INTO log (operation, date) VALUES (?,NOW());";
		Connection connection = DAOFactory.getInstance().getConnectionThread();

		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, fonction);
		ps.execute();

	}

}
