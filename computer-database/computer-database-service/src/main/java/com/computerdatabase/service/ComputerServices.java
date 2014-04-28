package com.computerdatabase.service;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.mapper.Mapper;
import com.computerdatabase.repositories.ComputerRepository;
import com.computerdatabase.repositories.LogRepository;
import com.computerdatabase.wrapper.PageWrapper;

@Service("computerServices")
@Transactional
public class ComputerServices {

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	private LogRepository logRepository;

	private final Logger logger = LoggerFactory
			.getLogger(CompanyServices.class);

	public void put(Computer computer) {
		logger.info("In create computer method");

		computerRepository.save(computer);

		Logs log = Logs.builder().operation("INSERT Computer")
				.name(computer.getName()).idComputer(computer.getId())
				.date(DateTime.now()).build();

		logRepository.save(log);
		logger.info("Quit create method");
	}

	public List<Computer> get(PageWrapper page) {
		Logs log = Logs.builder().date(DateTime.now())
				.operation("Get Computer").name(null).idComputer(0).build();

		logRepository.save(log);

		Page<Computer> pageRequested = computerRepository
				.findAll(constructPageSpecification(readSortSearch(page)));

		page.setComputers(Mapper.toDTO(pageRequested.getContent()));
		
		page.setNumberComputer(new Long(pageRequested.getTotalElements()).intValue());
		
		return pageRequested.getContent();
	}

	public Computer find(int id) {
		Computer computer = null;

		computer = computerRepository.findOne(new Long(id));

		Logs log = Logs.builder().date(DateTime.now())
				.operation("Find computer").name(null).idComputer(id).build();

		logRepository.save(log);

		return computer;
	}

	public void find(PageWrapper page) {
		Logs log = Logs.builder().date(DateTime.now())
				.operation("Find computer").name(page.getName()).idComputer(0)
				.build();

		logRepository.save(log);

		Page<Computer> pageRequested = computerRepository
				.findByNameContainingOrCompanyNameContaining("%"
						+ page.getName().trim() + "%", "%"
						+ page.getName().trim() + "%",
						constructPageSpecification(readSortSearch(page)));

		page.setComputers(Mapper.toDTO(pageRequested.getContent()));

		page.setNumberComputer(new Long(pageRequested.getTotalElements()).intValue());
	}

	public void update(Computer computer) {
		Logs log = Logs.builder().date(DateTime.now())
				.operation("Update computer").name(computer.getName())
				.idComputer(computer.getId()).build();

		logRepository.save(log);

		computerRepository.save(computer);

	}

	public void delete(int id) {
		Logs log = Logs.builder().date(DateTime.now())
				.operation("Delete computer").name(null).idComputer(id).build();

		logRepository.save(log);
		Computer computer = computerRepository.findOne(new Long(id));

		if (computer != null)
			computerRepository.delete(computer);
	}

	// Fonctions utiles
	private PageWrapper readSortSearch(PageWrapper page) {

		if (page.getSort() != null && !page.getSort().equals("company.name")
				&& !page.getSort().equals("name")
				&& !page.getSort().equals("introducedDate")
				&& !page.getSort().equals("discontinuedDate"))
			page.setSort("name");
		else if (page.getSort() == null)
			page.setSort("name");

		if (page.getOrdre() != null && !page.getOrdre().trim().equals("asc")
				&& !page.getOrdre().trim().equals("desc"))
			page.setOrdre("asc");
		else if (page.getOrdre() == null)
			page.setOrdre("asc");
		
		return page;

	}

	private Pageable constructPageSpecification(PageWrapper page) {
		Pageable pageSpecification = new PageRequest(page.getCurrentPage(),
				page.getNumberElement(), new Sort(
						(page.getOrdre().equals("asc")) ? Sort.Direction.ASC
								: Sort.Direction.DESC, page.getSort()));
		return pageSpecification;
	}
}
