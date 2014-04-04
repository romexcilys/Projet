package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.domain.Page;
import com.computerdatabase.dto.ComputerDTO;

@Service
public class ComputerServices {
	@Autowired
	private DAOFactory daoFactory;
	
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private LogDAO logDAO;
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	


	public DAOFactory getDaoFactory() {
		return daoFactory;
	}


	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}


	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}


	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}


	public LogDAO getLogDAO() {
		return logDAO;
	}


	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}


	public void put(Computer computer)
	{
		Connection connection = daoFactory.getConnectionThread();
		
		try {
			computerDAO.put(computer);
			Logs log = Logs.builder().operation("INSERT Computer").name(computer.getName()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in put ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		}finally
		{
			daoFactory.closeConnection();
		}
	}

	
	public void get(Page page)
	{
		Connection connection = daoFactory.getConnectionThread();
		int nombreComputers = 0;
		try {
			Logs log = Logs.builder().operation("Get Computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			computerDAO.get(page);
			
			nombreComputers = computerDAO.getNumber();
			
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			daoFactory.closeConnection();
		}
		
		page.setNumberComputer(nombreComputers);
	}
	
	public int getCount()
	{
		Connection connection = daoFactory.getConnectionThread();
		int number = 0;
		try {
			Logs log = Logs.builder().operation("Get numbers computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber();
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			daoFactory.closeConnection();
		}
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = daoFactory.getConnectionThread();
		int number = 0;
		
		try {
			Logs log = Logs.builder().operation("Get numbers computer : "+nom).name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber(nom);
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count with name ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			daoFactory.closeConnection();
		}
		
		return number;
	}
	
	public ComputerDTO find(int id)
	{
		Connection connection = daoFactory.getConnectionThread();
		ComputerDTO  computerDTO = null;
		try {
			computerDTO = computerDAO.find(id);
			Logs log = Logs.builder().operation("Find computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find with id ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			daoFactory.closeConnection();
		}
		
		return computerDTO;
	}
	
	public void find(Page page)
	{
		Connection connection = daoFactory.getConnectionThread();
		int nombreComputers = 0;
		
		try {
			Logs log = Logs.builder().operation("Find computer").name(page.getName()).idComputer(-1).build();
			logDAO.logOperation(log);
			computerDAO.findPage(page);
			nombreComputers = computerDAO.getNumber(page.getName());
			
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			daoFactory.closeConnection();
		}
		
		page.setNumberComputer(nombreComputers);
	}
	
	public void update(Computer computer)
	{
		Connection connection = daoFactory.getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Update computer").name(computer.getName()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			computerDAO.update(computer);
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in update ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} fianlly{
			daoFactory.closeConnection();
		}
		
	}
	
	public void delete(int id)
	{
		Connection connection = daoFactory.getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Delete computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			computerDAO.delete(id);
			daoFactory.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in delete ComputerServices");
			daoFactory.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			daoFactory.closeConnection();
		}
	}
}
