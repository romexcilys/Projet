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

public class ComputerDAO {

	private final Logger logger;
	private static ComputerDAO computerDao = null;

	private ComputerDAO() {
		logger = LoggerFactory.getLogger(ComputerDAO.class);
	}

	public static ComputerDAO getInstance() {
		if (computerDao == null)
			computerDao = new ComputerDAO();

		return computerDao;
	}

	public void put(Computer computer)
			throws SQLException {
		logger.info("In insererComputer with computer argument");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		
		String query;
		if (computer.getCompany().getId() != 0)
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
		else
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,null);";

		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, computer.getNom());
		ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().getTime()));

		ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate()
				.getTime()));

		if (computer.getCompany().getId() != 0)
			ps.setInt(4, computer.getCompany().getId());

		ps.execute();
		ps.close();

		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(String sort, String ordre)
			throws SQLException {
		logger.info("In getListComputer method");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		
		List<Computer> computers = new ArrayList<Computer>();

		String query = "";

		if (sort.equals("compa_name")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name ASC;";
		} else if (sort.equals("compu_name")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC;";
		} else if (sort.equals("intro_date")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced ASC;";
		} else if (sort.equals("discon_date")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued ASC;";
		} else {
			query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC;";
		}
		PreparedStatement stmt = null;
		ResultSet results = null;

		stmt = connection.prepareStatement(query);
		results = stmt.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			Date introduced = results.getDate("introduced");
			Date discontinued = results.getDate("discontinued");

			int compaId = results.getInt("id");
			String compaName = results.getString("compa_name");

			computers.add(Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).nom(compaName)
									.build()).build());
		}

		stmt.close();
		results.close();

		logger.info("Quit getListComputer method");
		return computers;
	}

	public List<Computer> get(int debut, int number, 
			String sort, String ordre) throws SQLException {
		logger.info("In getListComputer with arguments");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = new ArrayList<Computer>();

		String query = "";

		if (sort.equals("compa_name")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name ASC LIMIT ?, ?;";
		} else if (sort.equals("compu_name")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		} else if (sort.equals("intro_date")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced ASC LIMIT ?, ?;";
		} else if (sort.equals("discon_date")) {
			if (ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued ASC LIMIT ?, ?;";
		} else {
			query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, debut);
		ps.setInt(2, number);
		results = ps.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			Date introduced = results.getDate("introduced");
			Date discontinued = results.getDate("discontinued");

			int compaId = results.getInt("id");
			String compaName = results.getString("compa_name");

			computers.add(Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).nom(compaName)
									.build()).build());
		}

		ps.close();
		results.close();

		logger.info("Quit getListComputer method");
		return computers;
	}

	public int getNumber() throws SQLException {
		logger.info("In getNumberComputer method");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
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
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
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

	public List<Computer> find(String nom, String sort,
			String ordre) throws SQLException {
		logger.info("In searchComputer method");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = new ArrayList<Computer>();

		String query = "";

		if (sort.equals("compa_name")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name ASC;";
		} else if (sort.equals("compu_name")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
		} else if (sort.equals("intro_date")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced ASC;";
		} else if (sort.equals("discon_date")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued ASC;";
		} else {
			query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
		}

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, "%" + nom + "%");
		ps.setString(2, "%" + nom + "%");

		results = ps.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			Date introduced = results.getDate("introduced");
			Date discontinued = results.getDate("discontinued");

			int compaId = results.getInt("compaId");
			String compaName = results.getString("compa_name");

			computers.add(Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).nom(compaName)
									.build()).build());

		}

		results.close();
		ps.close();

		logger.info("Quit searchComputer method");

		return computers;
	}

	public void update(Computer computer)
			throws SQLException {
		logger.info("In editComputer method");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		String query;
		

		if (computer.getCompany().getId() != 0)
			query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
		else
			query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = null WHERE id = ?;";

		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, computer.getNom());
		ps.setDate(2, new java.sql.Date(computer.getIntroducedDate().getTime()));
		ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate()
				.getTime()));

		if (computer.getCompany().getId() != 0) {
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());
		} else
			ps.setInt(4, computer.getId());

		ps.execute();

		ps.close();

		logger.info("Quit editComputer method");

	}

	public void delete(int id) throws SQLException {

		logger.info("In deleteComputer method with id argument");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		
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
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		Computer computer = null;

		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued,  compa.name AS compa_name, compa.id AS compaID FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE compu.id = ?;";

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setInt(1, id);

		results = ps.executeQuery();

		while (results.next()) {
			String name = results.getString("compu_name");
			Date introduced = results.getDate("introduced");
			Date discontinued = results.getDate("discontinued");

			int compaId = results.getInt("compaID");
			String compaName = results.getString("compa_name");

			computer = Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).nom(compaName)
									.build()).build();

		}

		ps.close();
		results.close();

		logger.info("Quit findComputer method");

		return computer;
	}

	public List<Computer> find(String nom, int debut, int number,
			String sort, String ordre)
			throws SQLException {
		logger.info("In searchComputer method");
		
		Connection connection = DAOFactory.getInstance().getConnectionThread();
		List<Computer> computers = new ArrayList<Computer>();

		String query = "";

		if (sort.equals("compa_name")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name ASC LIMIT ?, ?;";
		} else if (sort.equals("compu_name")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		} else if (sort.equals("intro_date")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced ASC LIMIT ?, ?;";
		} else if (sort.equals("discon_date")) {
			if (ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued ASC LIMIT ?, ?;";
		} else {
			query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}

		PreparedStatement ps = null;
		ResultSet results = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, "%" + nom + "%");
		ps.setString(2, "%" + nom + "%");
		ps.setInt(3, debut);
		ps.setInt(4, number);

		results = ps.executeQuery();

		while (results.next()) {
			int id = results.getInt("compu_id");
			String name = results.getString("compu_name");
			Date introduced = results.getDate("introduced");
			Date discontinued = results.getDate("discontinued");

			int compaId = results.getInt("compaId");
			String compaName = results.getString("compa_name");

			computers.add(Computer
					.builder()
					.id(id)
					.name(name)
					.introduced(introduced)
					.discontinued(discontinued)
					.company(
							Company.builder().id(compaId).nom(compaName)
									.build()).build());

		}

		results.close();
		ps.close();

		logger.info("Quit searchComputer method");

		return computers;
	}
}