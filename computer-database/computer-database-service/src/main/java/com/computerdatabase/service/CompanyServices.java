package com.computerdatabase.service;

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

	private final Logger logger = LoggerFactory
			.getLogger(CompanyServices.class);

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
		logger.info("Get companies in method");
		
		List<Company> companys = null;
		Logs log = Logs.builder().operation("SELECT Companys").name(null)
				.idComputer(0).build();
		logDAO.create(log);
		companys = companyDAO.read();
		
		logger.info("Quit method get companies");
		return companys;
	}
}
