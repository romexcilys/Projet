package com.company.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionSingleton {
	
	private static String url = "jdbc:mysql://127.0.0.1/computer-database-db";
	private static String user = "jee-cdb";
	private static String password = "password";
	private static Connection connexion;
	
	public static Connection getInstance()
	{
		if(connexion == null)
		{
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connexion = DriverManager.getConnection(url, user, password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return connexion;
	}
	

}
