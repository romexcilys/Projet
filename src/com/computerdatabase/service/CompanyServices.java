package com.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.domain.Company;

public class CompanyServices {

	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	private LogDAO logDAO = LogDAO.getInstance();

	public List<Company> get() {
		Connection connection = ConnectionManager.getConnection();
		logDAO.logOperation("Get companys", connection);
		List<Company> companys = companyDAO.getListCompany(connection);

		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return companys;
	}

}
