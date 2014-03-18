package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LogDAO {
	
	private static LogDAO logDAO = null;
	
	public static LogDAO getInstance()
	{
		if(logDAO == null)
			logDAO = new LogDAO();
		
		return logDAO;
	}
	
	public void logOperation(String fonction, Connection con)
	{
		String query = "INSERT INTO log (operation, date) VALUES (?,NOW());";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, fonction);
			ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
}
