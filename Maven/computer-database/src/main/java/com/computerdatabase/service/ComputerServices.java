package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.domain.Page;
import com.computerdatabase.dto.ComputerDTO;

public enum ComputerServices {
	
	INSTANCE;
	
	private static ComputerDAO computerDAO = DAOFactory.INSTANCE.getComputerDAO();
	private static LogDAO logDAO = DAOFactory.INSTANCE.getLogDAO();
	private final Logger logger = LoggerFactory.getLogger(CompanyServices.class);
	
	
	public void put(Computer computer)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		
		try {
			computerDAO.put(computer);
			Logs log = Logs.builder().operation("INSERT Computer").name(computer.getNom()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in put ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		}finally
		{
			DAOFactory.INSTANCE.closeConnection();
		}
		
	}

	
	public void get(Page page)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		int nombreComputers = 0;
		try {
			Logs log = Logs.builder().operation("Get Computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			computerDAO.get(page);
			
			nombreComputers = computerDAO.getNumber();
			
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in get ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			DAOFactory.INSTANCE.closeConnection();
		}
		
		page.setNumberComputer(nombreComputers);
		
	}
	
	public int getCount()
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		int number = 0;
		try {
			Logs log = Logs.builder().operation("Get numbers computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber();
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.INSTANCE.closeConnection();
		}
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		int number = 0;
		
		try {
			Logs log = Logs.builder().operation("Get numbers computer : "+nom).name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber(nom);
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in count with name ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			DAOFactory.INSTANCE.closeConnection();
		}
		
		return number;
	}
	
	public ComputerDTO find(int id)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		ComputerDTO  computerDTO = null;
		try {
			computerDTO = computerDAO.find(id);
			Logs log = Logs.builder().operation("Find computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find with id ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.INSTANCE.closeConnection();
		}
		
		return computerDTO;
	}
	

	
	public void find(Page page)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		int nombreComputers = 0;
		
		try {
			Logs log = Logs.builder().operation("Find computer").name(page.getName()).idComputer(-1).build();
			logDAO.logOperation(log);
			computerDAO.findPage(page);
			nombreComputers = computerDAO.getNumber(page.getName());
			
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in find ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.INSTANCE.closeConnection();
		}
		
		page.setNumberComputer(nombreComputers);
		
	}
	
	public void update(Computer computer)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Update computer").name(computer.getNom()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			computerDAO.update(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in update ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.INSTANCE.commitConnection(connection);
		DAOFactory.INSTANCE.closeConnection();
	}
	
	public void delete(int id)
	{
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Delete computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			computerDAO.delete(id);
			DAOFactory.INSTANCE.commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Error in delete ComputerServices");
			DAOFactory.INSTANCE.rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.INSTANCE.closeConnection();
		}
		
	}
}
