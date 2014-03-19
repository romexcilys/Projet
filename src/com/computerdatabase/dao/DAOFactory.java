package com.computerdatabase.dao;

public class DAOFactory {
	
	
	public static CompanyDAO getCompanyDAO()
	{
		return CompanyDAO.getInstance();
	}
	
	public static ComputerDAO getComputerDAO()
	{
		return ComputerDAO.getInstance();
	}
	
	public static LogDAO getLogDAO()
	{
		return LogDAO.getInstance();
	}
	
}
