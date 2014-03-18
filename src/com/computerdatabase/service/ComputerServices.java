package com.computerdatabase.service;

import java.sql.Connection;
import java.util.List;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Computer;

public class ComputerServices {
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	private LogDAO logDAO = LogDAO.getInstance();
	
	
	public void put(Computer computer, Connection connection)
	{
		logDAO.logOperation("INSERT computer : "+computer.getNom()+" from company : "+computer.getCompany().getNom(),  connection);
		computerDAO.insererComputer(computer, connection);
	}
	
	public List<Computer> get(Connection connection)
	{
		logDAO.logOperation("Get computers",  connection);
		return computerDAO.getListComputer(connection);
	}
	
	public List<Computer> get(int debut, int number, Connection connection)
	{
		logDAO.logOperation("Get computers from "+debut+" to "+(debut+number),  connection);
		return computerDAO.getListComputer(debut, number, connection);
	}
	
	public int getCount(Connection connection)
	{
		logDAO.logOperation("Get numbers computer",  connection);
		return computerDAO.getNumberComputer(connection);
	}
	
	public int getCount(String nom, Connection connection)
	{
		logDAO.logOperation("Get numbers computer : "+nom,  connection);
		return computerDAO.getNumberComputer(nom, connection);
	}
	
	public Computer find(int id, Connection connection)
	{
		logDAO.logOperation("Find computer with id : "+id,  connection);
		return computerDAO.findComputer(id, connection);
	}
	
	public List<Computer> find(String nom, Connection connection)
	{
		logDAO.logOperation("Find computer with name : "+nom,  connection);
		return computerDAO.searchComputer(nom, connection);
	}
	
	public List<Computer> find(String nom, int debut, int number, Connection connection)
	{
		logDAO.logOperation("Find computer with name : "+nom+" from "+debut+" to "+(debut+number),  connection);
		return computerDAO.findComputer(nom, debut, number, connection);
	}
	
	public void edit(Computer computer, Connection connection)
	{
		logDAO.logOperation("Edit computer name : "+computer.getNom()+ " and id : "+computer.getId(),  connection);
		computerDAO.editComputer(computer, connection);
	}
	
	public void delete(int id, Connection connection)
	{
		logDAO.logOperation("Delete computer with id :"+id,  connection);
		computerDAO.deleteComputer(id, connection);
	}
	
	
}
