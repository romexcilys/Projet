package com.company.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.bean.Company;
import com.company.connexion.ConnexionSingleton;

public class CompanyDAO {
	
	public List<Company> getListCompany()
	{
		List<Company> companys = new ArrayList<Company>();
		
		String query = "SELECT * FROM company;";
		
		Statement stmt;
		ResultSet results;
		
		try {
			
			stmt = ConnexionSingleton.getInstance().createStatement();
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				companys.add(new Company(results.getInt("id"),results.getString("name")));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return companys;
	}

}
