package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dao.ConnexionSingleton;

public class ComputerDAO {
	
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
		} finally{
			try {
				
				ps.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Computer> getListComputer()
	{
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
		} finally{
			try {
				
				stmt.close();
				con.close();
				results.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return computers;	
	}
	
	public int getNumberComputer()
	{
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
		} finally{
			try {
				
				stmt.close();
				results.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return total;
	}
	
	public List<Computer> searchComputer(String nom)
	{
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
				
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
				
				int compaId = results.getInt("id");
				String compaName = results.getString("compa_name");
				
				computers.add(Computer.builder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(Company.builder().id(compaId).nom(compaName).build()).build());
				
				//computers.add(new Computer(results.getInt("compu_id"),results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"),new Company(results.getInt("id"), results.getString("compa_name"))));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				con.close();
				results.close();
				ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return computers;
	}
	
	public void editComputer(Computer computer)
	{
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ;";
		
		Connection con = null;
		PreparedStatement ps =null;
		
		
		try {
			
			con = ConnexionSingleton.getInstance();
			ps = con.prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().getTime()));
			ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate().getTime()));
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void deleteComputer(int id)
	{
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
		} finally{
			
			try {
				
				con.close();
				ps.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
