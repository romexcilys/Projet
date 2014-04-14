package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Page;

@Component
public class ComputerDAO {

	@Autowired
	private DataSource connectionPool;
	
	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public void put(Computer computer)
			throws SQLException {
		logger.info("In insererComputer with computer argument");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,");
		if(computer.getIntroducedDate() != null)
			query.append("?,");
		else
			query.append("'0000-00-00',");
		
		if(computer.getDiscontinuedDate() != null)
			query.append("?,");
		else
			query.append("'0000-00-00',");
		
		if (computer.getCompany().getId() != 0)
			query.append("?);");
		else
			query.append("null);");
/*
		if (computer.getCompany().getId() != 0)
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
		else
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,null);";
*/
		PreparedStatement ps = null;
		
		
		ps = connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, computer.getName());
		int position = 2;
		
		if(computer.getIntroducedDate() != null)
		{
			ps.setDate(position, new java.sql.Date(computer.getIntroducedDate().toDate().getTime()));
			position++;
		}
		
		if(computer.getDiscontinuedDate() != null)
		{
			ps.setDate(position, new java.sql.Date(computer.getDiscontinuedDate().toDate()
				.getTime()));
			position++;
		}

		if (computer.getCompany().getId() != 0)
		{
			ps.setInt(position, computer.getCompany().getId());
			position++;
		}
		
		int idComputer = ps.executeUpdate();
		ps.close();
		
		
		//RECHERCHE DE L'ID DU COMPUTER AJOUTER
		
		computer.setId(idComputer);

		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(Page page) throws SQLException {
		logger.info("In getListComputer with arguments");
		
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String sort = page.getSort();
		String ordre = page.getOrdre();
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		List<Computer> computers = new ArrayList<Computer>();

		StringBuilder query = new StringBuilder();
		
		query.append("SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ");
		
		if(sort != null)
		{
			query.append(" ORDER BY ");
			
			switch(sort)
			{
				case "compa_name":
					query.append("compa_name");
					break;
				case "compu_name":
					query.append("compu.name");
					break;
				case "intro_date":
					query.append("compu.introduced");
					break;
				case "discon_date":
					query.append("compu.discontinued");
					break;
				default:
					query.append("compa_name, compu.name");
					break;
			}
			
			if(ordre != null)
			{
				switch(ordre.trim())
				{
				case "desc":
					query.append(" DESC");
					break;
				case "asc":
					query.append(" ASC");
					break;
				default:
					query.append(" ASC");
					break;
				}
			}
		}
		
		if(debut != -1 && number != -1)
		{
			query.append(" LIMIT ").append(debut).append(", ").append(number);
		}
		
		query.append(";");
		
		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query.toString());
		results = ps.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			LocalDate introduced = null;
			if(results.getDate("introduced") != null)
				introduced = LocalDate.fromDateFields(results.getDate("introduced"));
			
			LocalDate discontinued = null;
			if(results.getDate("discontinued") != null)
				discontinued = LocalDate.fromDateFields(results.getDate("discontinued"));

			int compaId = results.getInt("id");
			String compaName = results.getString("compa_name");

			Computer computer = Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).name(compaName)
									.build()).build();
			
			computers.add(computer);
		}

		ps.close();
		results.close();

		logger.info("Quit getListComputer method");
		
		return computers;

	}

	public int getNumber() throws SQLException {
		logger.info("In getNumberComputer method");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		int total = 0;

		String query = "SELECT COUNT(*) as nombre FROM computer;";

		Statement stmt = null;
		ResultSet results = null;

		stmt = connection.createStatement();
		results = stmt.executeQuery(query);
		results.next();
		total = results.getInt("nombre");

		stmt.close();
		results.close();

		logger.info("Quit getNumberComputer method");

		return total;
	}

	public int getNumber(String nom) throws SQLException {
		logger.info("In getNumberComputer method with arguments");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		int total = 0;

		String query = "SELECT COUNT(*) AS nombre FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?;";

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, "%" + nom + "%");
		ps.setString(2, "%" + nom + "%");

		results = ps.executeQuery();
		results.next();
		total = results.getInt("nombre");

		ps.close();
		results.close();

		logger.info("Quit getNumberComputer method");

		return total;
	}

	
	///VOIR PROBLEME D'UPDATE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void update(Computer computer)
			throws SQLException {
		logger.info("In editComputer method");
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		StringBuilder query = new StringBuilder();
		
		query.append("UPDATE computer SET name = ?, introduced = ");
		
		if(computer.getIntroducedDate() == null)
			query.append("'0000-00-00', ");
		else
			query.append("?, ");
		
		
		query.append("discontinued = ");
		
		if(computer.getDiscontinuedDate() == null)
			query.append("'0000-00-00', ");
		else
			query.append("?, ");
		
		query.append("company_id = ");
		
		if(computer.getCompany().getId() != 0)
			query.append("? ");
		else
			query.append("null ");
		
		query.append(" WHERE id = ?;");
		
			//String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";

		PreparedStatement ps = null;

		ps = connection.prepareStatement(query.toString());
		ps.setString(1, computer.getName());

		
		int position = 2;
	
		if(computer.getIntroducedDate() != null)
		{
			ps.setDate(position, new java.sql.Date(computer.getIntroducedDate().toDate().getTime()));
			position++;
		}
		
		if(computer.getDiscontinuedDate() != null)
		{
			ps.setDate(position, new java.sql.Date(computer.getDiscontinuedDate().toDate().getTime()));
			position++;
		}
				

		if (computer.getCompany().getId() != 0) {
			ps.setInt(position, computer.getCompany().getId());
			position++;
		} 

		ps.setInt(position, computer.getId());

		ps.execute();

		ps.close();

		logger.info("Quit editComputer method");
	}

	public void delete(int id) throws SQLException {
		logger.info("In deleteComputer method with id argument");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		
		String query = "DELETE FROM computer WHERE id = ?;";

		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);

		ps.execute();

		ps.close();

		logger.info("Quit deleteComputer method");
	}

	public Computer find(int id) throws SQLException {
		logger.info("In findComputer method with id argument");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		Computer computer = null;

		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued,  compa.name AS compa_name, compa.id AS compaID FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE compu.id = ?;";

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);

		results = ps.executeQuery();

		while (results.next()) {
			String name = results.getString("compu_name");
			LocalDate introduced = null;
			
			if(results.getDate("introduced") != null)
				introduced = LocalDate.fromDateFields(results.getDate("introduced"));
			
			LocalDate discontinued = null;
			if(results.getDate("discontinued") != null)
				discontinued = LocalDate.fromDateFields(results.getDate("discontinued"));

			int compaId = results.getInt("compaID");
			String compaName = results.getString("compa_name");

			computer = Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).name(compaName)
									.build()).build();


		}

		ps.close();
		results.close();

		logger.info("Quit findComputer method");

		return computer;
	}

	public List<Computer> findPage(Page page)
			throws SQLException {
		
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String sort = page.getSort();
		String ordre = page.getOrdre();
		String nom = page.getSearchName();
		
		logger.info("In searchComputer method");
		
		Connection connection = DataSourceUtils.getConnection(connectionPool);
		List<Computer> computers = new ArrayList<Computer>();

		StringBuilder query = new StringBuilder();
		
		query.append("SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ? ");
		
		if(sort != null)
		{
			query.append(" ORDER BY ");
			
			switch(sort)
			{
				case "compa_name":
					query.append("compa_name");
					break;
				case "compu_name":
					query.append("compu.name");
					break;
				case "intro_date":
					query.append("compu.introduced");
					break;
				case "discon_date":
					query.append("compu.discontinued");
					break;
				default:
					query.append("compa_name, compu.name");
					break;
			}
			
			if(ordre != null)
			{
				switch(ordre.trim())
				{
				case "desc":
					query.append(" DESC");
					break;
				case "asc":
					query.append(" ASC");
					break;
				default:
					query.append(" ASC");
					break;
				}
			}
		}
		
		if(debut != -1 && number != -1)
		{
			query.append(" LIMIT ").append(debut).append(", ").append(number);
		}
		
		query.append(";");
		
		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query.toString());
		ps.setString(1, "%" + nom + "%");
		ps.setString(2, "%" + nom + "%");

		results = ps.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			LocalDate introduced = null;
			if(results.getDate("introduced") != null)
				introduced = LocalDate.fromDateFields(results.getDate("introduced"));
			
			LocalDate discontinued = null;
			if(results.getDate("discontinued") != null)
				discontinued = LocalDate.fromDateFields(results.getDate("discontinued"));

			int compaId = results.getInt("compaId");
			String compaName = results.getString("compa_name");
				
			Computer computer = Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).name(compaName)
									.build()).build();
			
			computers.add(computer);

		}
		
		results.close();
		ps.close();

		logger.info("Quit searchComputer method");
		
		return computers;
	}
}