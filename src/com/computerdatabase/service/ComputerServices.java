package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Page;
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
			logDAO.logOperation("INSERT computer : "+computer.getNom()+" from company : "+computer.getCompany().getNom()+" id = "+computer.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
	}

	
	public List<Computer> get(Page page)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		try {
			logDAO.logOperation("Get computers from "+page.getCurrentPage()+" to "+(page.getCurrentPage()+page.getNumberElement()));
			computers = computerDAO.get(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computers;
	}
	
	public int getCount()
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		int number = 0;
		try {
			logDAO.logOperation("Get numbers computer");
			number = computerDAO.getNumber();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		int number = 0;
		
		try {
			logDAO.logOperation("Get numbers computer : "+nom);
			number = computerDAO.getNumber(nom);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return number;
	}
	
	public ComputerDTO find(int id)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		ComputerDTO  computerDTO = null;
		try {
			computerDTO = computerDAO.find(id);
			logDAO.logOperation("Find computer with id : "+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computerDTO;
	}
	

	
	public List<Computer> find(Page page)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		try {
			logDAO.logOperation("Find computer with name : "+page.getName()+" from "+page.getCurrentPage()+" to "+(page.getCurrentPage()+page.getNumberElement()));
			computers = computerDAO.find(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computers;
	}
	
	public void update(Computer computer)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		try {
			logDAO.logOperation("Edit computer name : "+computer.getNom()+ " and id : "+computer.getId());
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
			logDAO.logOperation("Delete computer with id :"+id);
			computerDAO.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
	}
}
