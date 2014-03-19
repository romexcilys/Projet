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
	
	private final Logger logger;
	private XLogger loggerx;
	private LogDAO logDAO;
	private static CompanyDAO computerDao = null;
	
	private CompanyDAO()
	{
		logDAO = LogDAO.getInstance();
		loggerx = XLoggerFactory.getXLogger(CompanyDAO.class
			      .getName());
		logger = LoggerFactory.getLogger(CompanyDAO.class);
	}
	
	public static CompanyDAO getInstance()
	{
		if(computerDao == null)
			computerDao = new CompanyDAO();
		
		return computerDao;
	}
	
	public List<Company> get(Connection connection)
	{
		loggerx.entry();
		logger.info("In getListCompany method");
		List<Company> companys = new ArrayList<Company>();
		
		String query = "SELECT * FROM company ORDER BY name;";
		
		Statement stmt = null;
		ResultSet results = null;
		
		try {
			stmt = connection.createStatement();
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				int id = results.getInt("id");
				String nom = results.getString("name");
				companys.add(Company.builder().id(id).nom(nom).build());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("getListCompany error!!!!!!!!!");
			
			ConnectionManager.rollbackConnection(connection);
			
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
		
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(connection);
		}
		
		return companys;
	}

}
