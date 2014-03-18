package com.computerdatabase.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.computerdatabase.domain.Company;

public class CompanyDAO {
	
	final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	private XLogger loggerx = XLoggerFactory.getXLogger(CompanyDAO.class
		      .getName());
	private LogDAO logDAO = LogDAO.getInstance();

	
	
	private static CompanyDAO computerDao;
	
	private CompanyDAO()
	{
		
	}
	
	public static CompanyDAO getInstance()
	{
		if(computerDao == null)
			computerDao = new CompanyDAO();
		
		return computerDao;
	}
	
	public List<Company> getListCompany(Connection connection)
	{
		loggerx.entry();
		logger.info("In getListCompany method");
		List<Company> companys = new ArrayList<Company>();
		
		String query = "SELECT * FROM company ORDER BY name;";
		
		Statement stmt = null;
		ResultSet results = null;
		
		try {
			logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//con = ConnexionSingleton.getInstance();
			stmt = connection.createStatement();
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				int id = results.getInt("id");
				String nom = results.getString("name");
				companys.add(Company.builder().id(id).nom(nom).build());
				//companys.add(new Company(results.getInt("id"),results.getString("name")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("getListCompany error!!!!!!!!!");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			logDAO.logOperation("Problem in getting companys", connection);
			loggerx.catching(e);
		}finally
		{
			try {
				
				stmt.close();
				results.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
				logger.info("getListCompany error!!!!!!!!!");
			}
		}
		
		logger.info("Quit getListCompany method");
		loggerx.exit(companys);
		
		return companys;
	}

}
