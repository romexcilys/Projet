package com.computerdatabase.service;

import java.sql.SQLException;
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
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	public void put(Computer computer)
	{
		
		try {
			computerDAO.create(computer);
			Logs log = Logs.builder().operation("INSERT Computer").name(computer.getName()).idComputer(computer.getId()).build();
			logDAO.create(log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in put ComputerServices");
			e.printStackTrace();
		}
	}
	
	public void get(Page  page)
	{
		int nombreComputers = 0;
		try {
			Logs log = Logs.builder().operation("Get Computer").name(null).idComputer(-1).build();
			logDAO.create(log);
			List<Computer> computers = computerDAO.get(page);
			
			page.setComputers(Mapper.toDTO(computers));
			
			nombreComputers = computerDAO.getNumber();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get ComputerServices");
			e.printStackTrace();
		}
		
		page.setNumberComputer(nombreComputers);
	}

	public int getCount()
	{
		int number = 0;
		try {
			Logs log = Logs.builder().operation("Get numbers computer").name(null).idComputer(-1).build();
			logDAO.create(log);
			number = computerDAO.getNumber();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count ComputerServices");
			e.printStackTrace();
		}
		
		return number;
	}

	public int getCount(String nom)
	{
		int number = 0;
		
		try {
			Logs log = Logs.builder().operation("Get numbers computer : "+nom).name(null).idComputer(-1).build();
			logDAO.create(log);
			number = computerDAO.getNumber(nom);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count with name ComputerServices");
			e.printStackTrace();
		}
		
		return number;
	}

	public Computer find(int id)
	{
		Computer  computer = null;
		try {
			computer = computerDAO.read(id);
			Logs log = Logs.builder().operation("Find computer").name(null).idComputer(id).build();
			logDAO.create(log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find with id ComputerServices");
			e.printStackTrace();
		}
		
		return computer;
	}

	public void find(Page  page)
	{
		int nombreComputers = 0;
		
		try {
			Logs log = Logs.builder().operation("Find computer").name(page.getName()).idComputer(-1).build();
			logDAO.create(log);
			List<Computer> computers = computerDAO.readSearch(page);
		
			page.setComputers(Mapper.toDTO(computers));
			nombreComputers = computerDAO.getNumber(page.getName());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find ComputerServices");
			e.printStackTrace();
		}
		
		page.setNumberComputer(nombreComputers);
	}

	public void update(Computer computer)
	{
		try {
			Logs log = Logs.builder().operation("Update computer").name(computer.getName()).idComputer(computer.getId()).build();
			logDAO.create(log);
			computerDAO.update(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in update ComputerServices");
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id)
	{
		try {
			Logs log = Logs.builder().operation("Delete computer").name(null).idComputer(id).build();
			logDAO.create(log);
			computerDAO.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in delete ComputerServices");
			e.printStackTrace();
		}
	}
}
