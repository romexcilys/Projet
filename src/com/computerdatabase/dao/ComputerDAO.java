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

	final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private XLogger loggerx = XLoggerFactory.getXLogger(ComputerDAO.class
			.getName());
	
	private LogDAO logDAO = LogDAO.getInstance();

	private static ComputerDAO computerDao;

	private ComputerDAO() {
	}

	public static ComputerDAO getInstance() {
		if (computerDao == null)
			computerDao = new ComputerDAO();

		return computerDao;
	}

	public void insererComputer(Computer computer, Connection connection) {
		logger.info("In insererComputer with computer argument");
		loggerx.entry();
		String query;
		if (computer.getCompany().getId() != 0)
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
		else
			query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,null);";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroducedDate()
					.getTime()));

			ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate()
					.getTime()));

			if (computer.getCompany().getId() != 0)
				ps.setInt(4, computer.getCompany().getId());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			ConnectionManager.rollbackConnection(connection);
			
			logDAO.logOperation("Problem to put computer with name : "+computer.getNom(), connection);
		} finally {
			try {

				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}

		logger.info("Quit insererComputer method");
		loggerx.exit();
	}

	public List<Computer> getListComputer(Connection connection, String sort, String ordre) {
		logger.info("In getListComputer method");
		loggerx.entry();
		List<Computer> computers = new ArrayList<Computer>();
		
		String query = "";
		
		if(sort.equals("compa_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name ASC;";
		}
		else if(sort.equals("compu_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC;";
		}
		else if(sort.equals("intro_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced ASC;";
		}
		else if(sort.equals("discon_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued DESC;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued ASC;";
		}
		else
		{
			query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC;";
		}
		PreparedStatement stmt = null;
		ResultSet results = null;
		try {
			
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

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to get computers", connection);
		} finally {
			try {

				stmt.close();
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

	public List<Computer> getListComputer(int debut, int number,Connection connection, String sort, String ordre) {
		logger.info("In getListComputer with arguments");
		loggerx.entry(debut, number);
		List<Computer> computers = new ArrayList<Computer>();
		
		String query="";
		
		if(sort.equals("compa_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name ASC LIMIT ?, ?;";
		}
		else if(sort.equals("compu_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}
		else if(sort.equals("intro_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.introduced ASC LIMIT ?, ?;";
		}
		else if(sort.equals("discon_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued DESC LIMIT ?, ?;";
			else
				query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compu.discontinued ASC LIMIT ?, ?;";
		}
		else
		{
			query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compu.id, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}
		
		PreparedStatement ps = null;
		ResultSet results = null;
		try {
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

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to get computers from "+debut+" to "+(debut+number), connection);
		} finally {
			try {

				ps.close();
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

	public int getNumberComputer(Connection connection) {
		logger.info("In getNumberComputer method");
		loggerx.entry();
		int total = 0;

		String query = "SELECT COUNT(*) as nombre FROM computer;";

		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = connection.createStatement();
			results = stmt.executeQuery(query);
			results.next();
			total = results.getInt("nombre");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to get numbers computer", connection);
		} finally {
			try {

				stmt.close();
				results.close();

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

	public int getNumberComputer(String nom, Connection connection) {
		logger.info("In getNumberComputer method with arguments");
		loggerx.entry();
		int total = 0;

		String query = "SELECT COUNT(*) AS nombre FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?;";

		PreparedStatement ps = null;
		ResultSet results = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + nom + "%");
			ps.setString(2, "%" + nom + "%");

			results = ps.executeQuery();
			results.next();
			total = results.getInt("nombre");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			loggerx.catching(e1);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to get numbers computer with name : "+nom, connection);
		} finally {
			try {

				ps.close();
				results.close();

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

	public List<Computer> searchComputer(String nom, Connection connection, String sort, String ordre) {
		logger.info("In searchComputer method");
		loggerx.entry(nom);
		List<Computer> computers = new ArrayList<Computer>();

		String query="";
		
		if(sort.equals("compa_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name ASC;";
		}
		else if(sort.equals("compu_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
		}
		else if(sort.equals("intro_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced ASC;";
		}
		else if(sort.equals("discon_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued DESC;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued ASC;";
		}
		else
		{
			query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC;";
		}
		
		
		PreparedStatement ps = null;
		ResultSet results = null;

		try {
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to find computer with name : "+nom, connection);
		} finally {
			try {

				results.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}

		logger.info("Quit searchComputer method");
		loggerx.exit(computers);
		
		
		return computers;
	}

	public void editComputer(Computer computer, Connection connection) {
		logger.info("In editComputer method");
		loggerx.entry(computer);
		String query;

		if (computer.getCompany().getId() != 0)
			query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
		else
			query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = null WHERE id = ?;";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, computer.getNom());
			ps.setDate(2, new java.sql.Date(computer.getIntroducedDate()
					.getTime()));
			ps.setDate(3, new java.sql.Date(computer.getDiscontinuedDate()
					.getTime()));

			if (computer.getCompany().getId() != 0) {
				ps.setInt(4, computer.getCompany().getId());
				ps.setInt(5, computer.getId());
			} else
				ps.setInt(4, computer.getId());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to edit computer with id : "+computer.getId(), connection);
		} finally {

			try {
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

	public void deleteComputer(int id, Connection connection) {

		logger.info("In deleteComputer method with id argument");
		loggerx.entry(id);
		String query = "DELETE FROM computer WHERE id = ?;";

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to delete computer with id : "+id, connection);
		} finally {

			try {

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

	public Computer findComputer(int id, Connection connection) {
		logger.info("In findComputer method with id argument");
		loggerx.entry(id);
		Computer computer = null;

		
		
		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued,  compa.name AS compa_name, compa.id AS compaID FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE compu.id = ?;";

		PreparedStatement ps = null;
		ResultSet results = null;

		try {
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to find computer with id : "+computer.getId(), connection);
		} finally {
			try {
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

	public List<Computer> findComputer(String nom, int debut, int number, Connection connection, String sort, String ordre) {
		logger.info("In searchComputer method");
		loggerx.entry(nom);
		List<Computer> computers = new ArrayList<Computer>();

		String query="";
		
		if(sort.equals("compa_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name ASC LIMIT ?, ?;";
		}
		else if(sort.equals("compu_name"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}
		else if(sort.equals("intro_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.introduced ASC LIMIT ?, ?;";
		}
		else if(sort.equals("discon_date"))
		{
			if(ordre.equals("desc"))
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued DESC LIMIT ?, ?;";
			else
				query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compu.discontinued ASC LIMIT ?, ?;";
		}
		else
		{
			query = "SELECT compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued, compa.id AS compaID, compa.name AS compa_name FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?  ORDER BY compa.name, compu.name ASC LIMIT ?, ?;";
		}
		
		
		PreparedStatement ps = null;
		ResultSet results = null;

		try {
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loggerx.catching(e);
			
			ConnectionManager.rollbackConnection(connection);
			logDAO.logOperation("Problem to find computer with name : "+nom+" from "+debut +" to "+(debut+number), connection);
		} finally {
			try {

				results.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loggerx.catching(e);
			}
		}

		logger.info("Quit searchComputer method");
		loggerx.exit(computers);
		
		return computers;
	}
}