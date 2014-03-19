package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;

public class CompanyServices {

	private static  CompanyDAO companyDAO = DAOFactory.getInstance().getCompanyDAO();
	private static LogDAO logDAO = DAOFactory.getInstance().getLogDAO();
	
	private static CompanyServices companyServices = null;
	
	private CompanyServices()
	{
	}
			
	public static CompanyServices getInstance()
	{
		if(companyServices == null)
			companyServices = new CompanyServices();
		
		return companyServices;
	}
	
	public List<Company> get() {
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		
		List<Company> companys = null;
		try {
			logDAO.logOperation("Get companys");
			companys = companyDAO.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionManager.rollbackConnection(connection);
			e.printStackTrace();
		}

		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return companys;
	}
}
