package com.computerdatabase.service;

import java.sql.Connection;
import java.util.List;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.DAOFactory;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;

public class ComputerServices {
	private ComputerDAO computerDAO;
	private LogDAO logDAO;
	private static ComputerServices computerServices = null;
	
	private ComputerServices()
	{
		computerDAO = DAOFactory.getComputerDAO();
		logDAO = DAOFactory.getLogDAO();
	}
	
	public static ComputerServices getInstance()
	{
		if(computerServices == null)
			computerServices = new ComputerServices();
		
		return computerServices;
	}
	
	public void put(Computer computer)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("INSERT computer : "+computer.getNom()+" from company : "+computer.getCompany().getNom(),  connection);
		computerDAO.put(computer, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
	
	public List<Computer> get(String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get computers",  connection);
		
		List<Computer> computers = computerDAO.get(connection, sort, ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public List<Computer> get(int debut, int number,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get computers from "+debut+" to "+(debut+number),  connection);
		
		List<Computer> computers = computerDAO.get(debut, number, connection, sort, ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public int getCount()
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get numbers computer",  connection);
		
		int number = computerDAO.getNumber(connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get numbers computer : "+nom,  connection);
		
		int number = computerDAO.getNumber(nom, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return number;
	}
	
	public Computer find(int id)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with id : "+id,  connection);
		
		Computer computer = computerDAO.find(id, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computer;
	}
	
	public List<Computer> find(String nom,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with name : "+nom,  connection);
		
		List<Computer> computers = computerDAO.find(nom, connection,sort,ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public List<Computer> find(String nom, int debut, int number,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with name : "+nom+" from "+debut+" to "+(debut+number),  connection);
		
		List<Computer> computers = computerDAO.find(nom, debut, number, connection,sort,ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public void update(Computer computer)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Edit computer name : "+computer.getNom()+ " and id : "+computer.getId(),  connection);
		computerDAO.update(computer, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
	
	public void delete(int id)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Delete computer with id :"+id,  connection);
		computerDAO.delete(id, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
}
