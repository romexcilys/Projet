package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Page;
import com.computerdatabase.domain.Logs;
import com.computerdatabase.dto.ComputerDTO;

public class ComputerServices {
	private static ComputerDAO computerDAO = DAOFactory.getInstance().getComputerDAO();
	private static LogDAO logDAO = DAOFactory.getInstance().getLogDAO();
	private static ComputerServices computerServices = null;
	
	private ComputerServices()
	{
	}
	
	public static ComputerServices getInstance()
	{
		if(computerServices == null)
			computerServices = new ComputerServices();
		
		return computerServices;
	}
	
	public void put(Computer computer)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		
		try {
			computerDAO.put(computer);
			Logs log = Logs.builder().operation("INSERT Computer").name(computer.getNom()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}finally
		{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
	}

	
	public <T>List<T> get(Page<T> page)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<T> computers = null;
		try {
			Logs log = Logs.builder().operation("Get Computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			computers = computerDAO.get(page);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
		
		return computers;
	}
	
	public int getCount()
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		int number = 0;
		try {
			Logs log = Logs.builder().operation("Get numbers computer").name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber();
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		int number = 0;
		
		try {
			Logs log = Logs.builder().operation("Get numbers computer : "+nom).name(null).idComputer(-1).build();
			logDAO.logOperation(log);
			number = computerDAO.getNumber(nom);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally
		{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
		
		
		return number;
	}
	
	public ComputerDTO find(int id)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		ComputerDTO  computerDTO = null;
		try {
			computerDTO = computerDAO.find(id);
			Logs log = Logs.builder().operation("Find computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
		
		return computerDTO;
	}
	

	
	public <T>List<T> find(Page<T> page)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<T> computers = null;
		try {
			Logs log = Logs.builder().operation("Find computer").name(page.getName()).idComputer(-1).build();
			logDAO.logOperation(log);
			computers = computerDAO.find(page);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
		
		return computers;
	}
	
	public void update(Computer computer)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Update computer").name(computer.getNom()).idComputer(computer.getId()).build();
			logDAO.logOperation(log);
			computerDAO.update(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
	}
	
	public void delete(int id)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		try {
			Logs log = Logs.builder().operation("Delete computer").name(null).idComputer(id).build();
			logDAO.logOperation(log);
			computerDAO.delete(id);
			DAOFactory.getInstance().commitConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		} finally{
			DAOFactory.getInstance().closeConnection();
		}
		
		
		
	}
}
