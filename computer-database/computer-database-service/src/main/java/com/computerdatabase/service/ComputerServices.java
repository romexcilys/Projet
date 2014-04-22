package com.computerdatabase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.mapper.Mapper;
import com.computerdatabase.wrapper.Page;

@Service
@Transactional
public class ComputerServices {

	@Autowired
	private ComputerDAO computerDAO;

	@Autowired
	private LogDAO logDAO;
	private final Logger logger = LoggerFactory
			.getLogger(CompanyServices.class);

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void put(Computer computer) {
		logger.info("In create computer method");
		
		computerDAO.create(computer);
		Logs log = Logs.builder().operation("INSERT Computer")
				.name(computer.getName()).idComputer(computer.getId()).build();
		logDAO.create(log);

		logger.info("Quit create method");
	}

	public void get(Page page) {
		int nombreComputers = 0;
		Logs log = Logs.builder().operation("Get Computer").name(null)
				.idComputer(0).build();
		logDAO.create(log);

		readSortSearch(page);
		
		List<Computer> computers = computerDAO.get(page);

		page.setComputers(Mapper.toDTO(computers));

		nombreComputers = computerDAO.getNumber();

		page.setNumberComputer(nombreComputers);
	}

	public int getCount() {
		int number = 0;
		Logs log = Logs.builder().operation("Get numbers computer").name(null)
				.idComputer(0).build();
		logDAO.create(log);
		number = computerDAO.getNumber();

		return number;
	}

	public int getCount(String nom) {
		int number = 0;

		Logs log = Logs.builder().operation("Get numbers computer : " + nom)
				.name(null).idComputer(0).build();
		logDAO.create(log);
		number = computerDAO.getNumber(nom);

		return number;
	}

	public Computer find(int id) {
		Computer computer = null;

		computer = computerDAO.read(id);
		Logs log = Logs.builder().operation("Find computer").name(null)
				.idComputer(id).build();
		logDAO.create(log);

		return computer;
	}

	public void find(Page page) {
		int nombreComputers = 0;

		Logs log = Logs.builder().operation("Find computer")
				.name(page.getName()).idComputer(0).build();
		logDAO.create(log);
		
		readSortSearch(page);
		
		List<Computer> computers = computerDAO.readSearch(page);

		page.setComputers(Mapper.toDTO(computers));
		nombreComputers = computerDAO.getNumber(page.getName());

		page.setNumberComputer(nombreComputers);
	}

	public void update(Computer computer) {
		Logs log = Logs.builder().operation("Update computer")
				.name(computer.getName()).idComputer(computer.getId()).build();
		logDAO.create(log);
		computerDAO.update(computer);

	}

	public void delete(int id) {
		Logs log = Logs.builder().operation("Delete computer").name(null)
				.idComputer(id).build();
		logDAO.create(log);
		computerDAO.delete(id);

	}

	private void readSortSearch(Page page) {
		if (page.getSort() != null) {

			switch (page.getSort()) {
			case "compa_name":
				page.setSort("company.name");
				break;
			case "compu_name":
				page.setSort("computer.name");
				break;
			case "intro_date":
				page.setSort("computer.introducedDate");
				break;
			case "discon_date":
				page.setSort("computer.discontinuedDate");
				break;
			default:
				page.setSort("company.name, computer.name");
				break;
			}
		}
		else if(page.getSort() != null)
			page.setSort("company.name, computer.name");
		
		if (page.getOrdre() != null
				&& !page.getOrdre().trim().equals("asc") && !page.getOrdre()
						.trim().equals("desc"))
			page.setOrdre("asc");
		else if(page.getOrdre() == null)
			page.setOrdre("asc");

	}
}
