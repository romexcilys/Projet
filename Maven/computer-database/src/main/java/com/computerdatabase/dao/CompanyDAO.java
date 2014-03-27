package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerdatabase.domain.Company;

public enum CompanyDAO {
	
	INSTANCE;
	
	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);



	public List<Company> get() throws SQLException {
		logger.info("In getListCompany method");
		Connection connection = DAOFactory.INSTANCE.getConnectionThread();
		
		List<Company> companys = new ArrayList<Company>();

		String query = "SELECT * FROM company ORDER BY name;";

		Statement stmt = null;
		ResultSet results = null;

		stmt = connection.createStatement();
		results = stmt.executeQuery(query);

		while (results.next()) {
			int id = results.getInt("id");
			String nom = results.getString("name");
			companys.add(Company.builder().id(id).nom(nom).build());
		}

		stmt.close();
		results.close();

		logger.info("Quit getListCompany method");

		return companys;
	}

}
