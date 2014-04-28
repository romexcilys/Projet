package com.computerdatabase.service;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.repositories.CompanyRepository;
import com.computerdatabase.repositories.LogRepository;

@Service("companyServices")
public class CompanyServices {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private LogRepository logRepository;


	private final Logger logger = LoggerFactory
			.getLogger(CompanyServices.class);

	@Transactional
	public List<Company> get() {
		logger.info("Get companies in method");
		
		List<Company> companys = null;
		Logs log = Logs.builder().operation("SELECT Companys").name(null)
				.idComputer(0).date(DateTime.now()).build();
		
		logRepository.save(log);
		
		companys = companyRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		logger.info("Quit method get companies");
		return companys;
	}
}
