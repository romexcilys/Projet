package com.computerdatabase.service;

import java.sql.Connection;
import java.util.List;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;

public class ComputerServices {
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	private LogDAO logDAO = LogDAO.getInstance();
	//private Connection connection = ConnectionManager.getConnection();
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	public List<Company> getCompany()
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get companys",  connection);
		List<Company> companys = companyDAO.getListCompany(connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return companys;
	}
	
	public void put(Computer computer)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("INSERT computer : "+computer.getNom()+" from company : "+computer.getCompany().getNom(),  connection);
		computerDAO.insererComputer(computer, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
	
	public List<Computer> get(String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get computers",  connection);
		
		List<Computer> computers = computerDAO.getListComputer(connection, sort, ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public List<Computer> get(int debut, int number,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get computers from "+debut+" to "+(debut+number),  connection);
		
		List<Computer> computers = computerDAO.getListComputer(debut, number, connection, sort, ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public int getCount()
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get numbers computer",  connection);
		
		int number = computerDAO.getNumberComputer(connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return number;
	}
	
	public int getCount(String nom)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get numbers computer : "+nom,  connection);
		
		int number = computerDAO.getNumberComputer(nom, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return number;
	}
	
	public Computer find(int id)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with id : "+id,  connection);
		
		Computer computer = computerDAO.findComputer(id, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computer;
	}
	
	public List<Computer> find(String nom,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with name : "+nom,  connection);
		
		List<Computer> computers = computerDAO.searchComputer(nom, connection,sort,ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public List<Computer> find(String nom, int debut, int number,String sort, String ordre)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Find computer with name : "+nom+" from "+debut+" to "+(debut+number),  connection);
		
		List<Computer> computers = computerDAO.findComputer(nom, debut, number, connection,sort,ordre);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
		
		return computers;
	}
	
	public void edit(Computer computer)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Edit computer name : "+computer.getNom()+ " and id : "+computer.getId(),  connection);
		computerDAO.editComputer(computer, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
	
	public void delete(int id)
	{
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Delete computer with id :"+id,  connection);
		computerDAO.deleteComputer(id, connection);
		
		ConnectionManager.commitConnection(connection);
		ConnectionManager.closeConnection(connection);
	}
}
