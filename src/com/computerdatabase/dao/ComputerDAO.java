package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dao.ConnexionSingleton;

public class ComputerDAO {
	
	final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	private XLogger loggerx = XLoggerFactory.getXLogger(ComputerDAO.class
		      .getName());

	
	private static ComputerDAO computerDao;
	
	private ComputerDAO()
	{
	}
	
	public static ComputerDAO getInstance()
	{
		if(computerDao == null)
			computerDao = new ComputerDAO();
		
		return computerDao;
	}
	
	public void insererComputer(Computer computer)
	{
		logger.info("In insererComputer with computer argument");
		loggerx.entry();
		String query;
		if(computer.getCompany().getId() != 0)
			 query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
		else
			 query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,null);";
		
		
		PreparedStatement ps = null ;
		Connection con = null;
		try {
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().getTime()));
			
			ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate().getTime()));
			
			if(computer.getCompany().getId() != 0)
				ps.setInt(4, computer.getCompany().getId());
			
			ps.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);

		} finally{
			try {
				
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}
		
		logger.info("Quit insererComputer method");
		loggerx.exit();
	}
	
	public List<Computer> getListComputer()
	{
		logger.info("In getListComputer method");
		loggerx.entry();
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC;";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet results = null;
		try {
			con = ConnexionSingleton.getInstance();
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
						
			while(results.next())
			{
				int id = results.getInt("compu_id");
				String name = results.getString("compu_name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				
				int compaId = results.getInt("id");
				String compaName = results.getString("compa_name");
				
				computers.add(Computer.builder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(Company.builder().id(compaId).nom(compaName).build()).build());
				//computers.add(new Computer(results.getInt("compu_id"), results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"), new Company(results.getInt("id"), results.getString("compa_name"))));
			}
			
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
		} finally{
			try {
				
				stmt.close();
				con.close();
				results.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}
		

		loggerx.exit(computers);
		logger.info("Quit getListComputer method");
		return computers;	
	}
	
	public List<Computer> getListComputer(int debut, int number)
	{
		logger.info("In getListComputer with arguments");
		loggerx.entry(debut, number);
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet results = null;
		try {
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setInt(1, debut);
			ps.setInt(2, number);
			results = ps.executeQuery();
						
			while(results.next())
			{
				int id = results.getInt("compu_id");
				String name = results.getString("compu_name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				
				int compaId = results.getInt("id");
				String compaName = results.getString("compa_name");
				
				computers.add(Computer.builder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(Company.builder().id(compaId).nom(compaName).build()).build());
				//computers.add(new Computer(results.getInt("compu_id"), results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"), new Company(results.getInt("id"), results.getString("compa_name"))));
			}
			
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
		} finally{
			try {
				
				ps.close();
				con.close();
				results.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}

		loggerx.exit(computers);
		logger.info("Quit getListComputer method");
		return computers;
	}
	
	public int getNumberComputer()
	{
		logger.info("In getNumberComputer method");
		loggerx.entry();
		int total = 0;
		
		String query = "SELECT COUNT(*) as nombre FROM computer;";
		
		Statement stmt = null;
		ResultSet results = null;
		Connection con = null;
		try {
			con = ConnexionSingleton.getInstance();
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			results.next();
			total = results.getInt("nombre");
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
		} finally{
			try {
				
				stmt.close();
				results.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}
		
		logger.info("Quit getNumberComputer method");
		loggerx.exit(total);
		return total;
	}
	
	public List<Computer> searchComputer(String nom)
	{
		logger.info("In searchComputer method");
		loggerx.entry(nom);
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
				
		Connection con = null;
		PreparedStatement ps = null ;
		ResultSet results = null;
		
		try {
			
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setString(1,"%"+nom+"%");
			ps.setString(2,"%"+nom+"%");
			
			results = ps.executeQuery();
			
			while(results.next())
			{
				int id = results.getInt("compu_id");
				String name = results.getString("compu_name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				
				int compaId = results.getInt("compaId");
				String compaName = results.getString("compa_name");
				
				computers.add(Computer.builder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(Company.builder().id(compaId).nom(compaName).build()).build());
				
				//computers.add(new Computer(results.getInt("compu_id"),results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"),new Company(results.getInt("id"), results.getString("compa_name"))));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
		}finally{
			try {
				
				con.close();
				results.close();
				ps.close();
				
			} catch (SQLException e) {loggerx.entry();
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}

		logger.info("Quit searchComputer method");
		loggerx.exit(computers);
		return computers;
	}
	
	public void editComputer(Computer computer)
	{
		logger.info("In editComputer method");
		loggerx.entry(computer);
		String query;
		
		if(computer.getCompany().getId() != 0)
			 query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
		else
			 query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = null WHERE id = ?;";
		
		Connection con = null;
		PreparedStatement ps =null;
		
		try {
			
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().getTime()));
			ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate().getTime()));
			
			if(computer.getCompany().getId() != 0)
			{
				ps.setInt(4, computer.getCompany().getId());
				ps.setInt(5, computer.getId());
			}
			else
				ps.setInt(4, computer.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
		} finally{
			
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
			
		}
		logger.info("Quit editComputer method");
		loggerx.exit();
	}
	
	public void deleteComputer(int id)
	{
		
		logger.info("In deleteComputer method with id argument");
		loggerx.entry(id);
		String query = "DELETE FROM computer WHERE id = ?;";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
		} finally{
			
			try {
				
				con.close();
				ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}
		logger.info("Quit deleteComputer method");
		loggerx.exit();
	}
	
	public Computer findComputer(int id)
	{
		logger.info("In findComputer method with id argument");
		loggerx.entry(id);
		Computer computer = null;
		
		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued,  compa.name AS compa_name, compa.id AS compaID FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE compu.id = ?;";
		
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet results = null;
		
		try {
			
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			results = ps.executeQuery();
			
			while(results.next())
			{
				String name = results.getString("compu_name");
				Date introduced = results.getDate("introduced");
				Date discontinued = results.getDate("discontinued");
				
				int compaId = results.getInt("compaID");
				String compaName = results.getString("compa_name");
				
				computer = Computer.builder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(Company.builder().id(compaId).nom(compaName).build()).build();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
		} finally {
			try {
				con.close();
				ps.close();
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}
		logger.info("Quit findComputer method");
		loggerx.exit(computer);
		
		return computer;		
	}
}
