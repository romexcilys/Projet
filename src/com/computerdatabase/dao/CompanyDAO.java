package com.computerdatabase.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.computerdatabase.domain.Company;
import com.computerdatabase.dao.ConnexionSingleton;

public class CompanyDAO {
	
	
	private static CompanyDAO computerDao;
	
	private CompanyDAO()
	{
		
	}
	
	public static CompanyDAO getInstance()
	{
		if(computerDao == null)
			computerDao = new CompanyDAO();
		
		return computerDao;
	}
	
	public List<Company> getListCompany()
	{
		List<Company> companys = new ArrayList<Company>();
		
		String query = "SELECT * FROM company;";
		
		Statement stmt = null;
		ResultSet results = null;
		Connection con = null;
		
		try {
			con = ConnexionSingleton.getInstance();
			stmt = con.createStatement();
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				int id = results.getInt("id");
				String nom = results.getString("name");
				companys.add(Company.builder().id(id).nom(nom).build());
				//companys.add(new Company(results.getInt("id"),results.getString("name")));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				
				stmt.close();
				results.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return companys;
	}

}
