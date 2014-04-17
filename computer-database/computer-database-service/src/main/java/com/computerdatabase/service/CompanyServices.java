package com.computerdatabase.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Logs;

@Service
public class CompanyServices {

	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private LogDAO logDAO;
	
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	
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

	@Transactional
	public List<Company> get() {
		
		List<Company> companys = null;
		try {
			Logs log = Logs.builder().operation("SELECT Companys").name(null).idComputer(-1).build();
			logDAO.create(log);
			companys = companyDAO.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get CompanyServices");
			e.printStackTrace();
		}

		return companys;
	}
}
