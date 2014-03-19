package com.computerdatabase.service;

import java.sql.Connection;
import java.util.List;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;

public class CompanyServices {

	private CompanyDAO companyDAO;
	private LogDAO logDAO;
	
	private static CompanyServices companyServices = null;
	
	private CompanyServices()
	{
		companyDAO = DAOFactory.getCompanyDAO();
		logDAO = DAOFactory.getLogDAO();
	}
			
	public static CompanyServices getInstance()
	{
		if(companyServices == null)
			companyServices = new CompanyServices();
		
		return companyServices;
	}
	
	public List<Company> get() {
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get companys", connection);
		List<Company> companys = companyDAO.get(connection);

		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return companys;
	}
}
