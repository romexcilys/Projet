package com.company.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.bean.Computer;
import com.company.connexion.ConnexionSingleton;

public class ComputerDAO {
	
	public void insererComputer(Computer computer)
	{
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
		PreparedStatement ps = null ;
		try {
			
			ps = ConnexionSingleton.getInstance().prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroduced_date().getTime()));
			System.out.println("Date 1 : "+new java.sql.Date(computer.getIntroduced_date().getTime()));
			
			ps.setDate(3, new java.sql.Date(computer.getDiscontinued_date().getTime()));
			
			System.out.println("Date 2 : "+new java.sql.Date(computer.getDiscontinued_date().getTime()));
			ps.setInt(4, computer.getCompany());
			
			ps.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DANS INSERER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		} finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Computer> getListComputer()
	{
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "SELECT compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id;";
		
		Statement stmt = null;
		ResultSet results;
		try {
			
			stmt = ConnexionSingleton.getInstance().createStatement();
			results = stmt.executeQuery(query);
						
			while(results.next())
			{
				
				computers.add(new Computer(results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"),results.getInt("id"), results.getString("compa_name")));

			}
			
			
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("DANS SELECTION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		} finally{
			try {
				stmt.close();
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
		ResultSet results;
		
		try {
			
			stmt = ConnexionSingleton.getInstance().createStatement();
			results = stmt.executeQuery(query);
			results.next();
			total = results.getInt("nombre");
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			try {
				stmt.close();
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
		
		String query = "SELECT compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu INNER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) = ?"
				+ " UNION "
				+ "SELECT compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu INNER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compu.name) = ? ;";
		PreparedStatement ps = null ;
		ResultSet results;
		
		try {
			
			ps = ConnexionSingleton.getInstance().prepareStatement(query);
			ps.setString(1,nom);
			ps.setString(2,nom);
			
			results = ps.executeQuery();
			
			while(results.next())
			{
				computers.add(new Computer(results.getString("compu_name"), results.getDate("introduced"), results.getDate("discontinued"),results.getInt("id"), results.getString("compa_name")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return computers;
	}
	
}
