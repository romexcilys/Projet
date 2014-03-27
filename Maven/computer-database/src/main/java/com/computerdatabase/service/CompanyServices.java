package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Logs;

public enum CompanyServices {

	INSTANCE;
	
	private static  CompanyDAO companyDAO = DAOFactory.INSTANCE.getCompanyDAO();
	private static LogDAO logDAO = DAOFactory.INSTANCE.getLogDAO();
	
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	
	
	public List<Company> get() {
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		
		List<Company> companys = null;
		try {
			Logs log = Logs.builder().operation("SELECT Companys").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			companys = companyDAO.get();
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get CompanyServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		}finally{
			DAOFactory.INSTANCE.closeConnection();
		}

		return companys;
	}
}
