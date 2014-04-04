package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Logs;

//@Transactional
@Service
public class CompanyServices {

	@Autowired
	private DAOFactory daoFactory;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private LogDAO logDAO;
	
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	//@Transactional(readOnly=true)
	public List<Company> get() {
		Connection connection = daoFactory.getConnectionThread();
		
		List<Company> companys = null;
		try {
			Logs log = Logs.builder().operation("SELECT Companys").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			companys = companyDAO.get();
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get CompanyServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		}finally{
			daoFactory.closeConnection();
		}

		return companys;
	}
}
