package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;

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
			logDAO.logOperation("INSERT computer : "+computer.getNom()+" from company : "+computer.getCompany().getNom());
			computerDAO.put(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
	}
	
	public List<Computer> get(String sort, String ordre)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		
		try {
			logDAO.logOperation("Get computers");
			computers = computerDAO.get(sort, ordre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computers;
	}
	
	public List<Computer> get(int debut, int number,String sort, String ordre)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		try {
			logDAO.logOperation("Get computers from "+debut+" to "+(debut+number));
			computers = computerDAO.get(debut, number, sort, ordre);
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
	
	public Computer find(int id)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		Computer  computer = null;
		try {
			computer = computerDAO.find(id);
			logDAO.logOperation("Find computer with id : "+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computer;
	}
	
	public List<Computer> find(String nom,String sort, String ordre)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		try {
			logDAO.logOperation("Find computer with name : "+nom);
			computers = computerDAO.find(nom, sort,ordre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DAOFactory.getInstance().rollbackConnection(connection);
			e.printStackTrace();
		}
		
		DAOFactory.getInstance().commitConnection(connection);
		DAOFactory.getInstance().closeConnection();
		
		return computers;
	}
	
	public List<Computer> find(String nom, int debut, int number,String sort, String ordre)
	{
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = null;
		try {
			logDAO.logOperation("Find computer with name : "+nom+" from "+debut+" to "+(debut+number));
			computers = computerDAO.find(nom, debut, number, sort, ordre);
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
