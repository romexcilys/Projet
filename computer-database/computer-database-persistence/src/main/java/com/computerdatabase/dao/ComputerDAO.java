package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.wrapper.Page;

@Component
public class ComputerDAO {
	@Autowired
	private DataSource connectionPool;
	
	JdbcTemplate jdbcTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public void create(Computer computer)
			throws SQLException {
		logger.info("In insererComputer with computer argument");
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		final String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);";
		final Computer computerer = computer;
		
		System.out.println(computerer);
		System.out.println(computer);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
			{
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, computerer.getName());
				
				ps.setDate(2, (computerer.getIntroducedDate() != null)? new java.sql.Date(computerer.getIntroducedDate().toDate().getTime()) : null);
				
				
				ps.setDate(3, (computerer.getDiscontinuedDate() != null)? new java.sql.Date(computerer.getDiscontinuedDate().toDate()
					.getTime()) : null);

				if (computerer.getCompany().getId() != 0)
					ps.setInt(4, computerer.getCompany().getId());
				else
					ps.setNull(4, java.sql.Types.BIGINT);
				
				return ps;
			}	
		}, keyHolder);
		
		//EDIT DE L'ID DU COMPUTER AJOUTER
		computer.setId(keyHolder.getKey().intValue());

		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(Page page) throws SQLException {
		logger.info("In getListComputer with arguments");
		
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String sort = page.getSort();
		String ordre = page.getOrdre();

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
	
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		List<Computer> computers = this.jdbcTemplate.query(query.toString(),
				new RowMapper<Computer>(){
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Computer computer = Computer
						.builder()
						.id(rs.getInt("compu_id"))
						.name(rs.getString("compu_name"))
						.company(
								Company.builder().id(rs.getInt("id")).name(rs.getString("compa_name"))
										.build()).build();
				
				if(rs.getDate("introduced") != null)
					computer.setIntroducedDate(LocalDate.fromDateFields(rs.getDate("introduced")));
				
				if(rs.getDate("discontinued") != null)
					computer.setDiscontinuedDate(LocalDate.fromDateFields(rs.getDate("discontinued")));
				
				
				return computer;
			}
			
		});
		
		logger.info("Quit getListComputer method");
		
		return computers;
	}

	public int getNumber() throws SQLException {
		logger.info("In getNumberComputer method");
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		String query = "SELECT COUNT(*) as nombre FROM computer;";
		int total =0;
		
		total = jdbcTemplate.queryForObject(query, Long.class).intValue();
		
		logger.info("Quit getNumberComputer method");
		
		return total;
	}

	public int getNumber(String nom) throws SQLException {
		logger.info("In getNumberComputer method with arguments");
		
		int total = 0;
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		String query = "SELECT COUNT(*) AS nombre FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE LOWER(compa.name) LIKE ? OR LOWER(compu.name) LIKE ?;";

		total = jdbcTemplate.queryForObject(query, new Object[]{"%" + nom + "%", "%" + nom + "%"}, Long.class).intValue();
		
		logger.info("Quit getNumberComputer method");

		return total;
	}
	
	public void update(Computer computer)
			throws SQLException {
		logger.info("In editComputer method");
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		final String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
		final Computer computerer = computer;
			//String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";

		
		jdbcTemplate.update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
			{
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, computerer.getName());
				
				ps.setDate(2, (computerer.getIntroducedDate() != null) ? new java.sql.Date(computerer.getIntroducedDate().toDate().getTime()) : null);
				
				ps.setDate(3, (computerer.getDiscontinuedDate() != null) ? new java.sql.Date(computerer.getDiscontinuedDate().toDate()
					.getTime()) : null);

				ps.setInt(4, (computerer.getCompany().getId() != 0) ? computerer.getCompany().getId() : 0);
				
				ps.setInt(5, computerer.getId());
				
				return ps;
			}	
		});
		
		logger.info("Quit editComputer method");
	}

	public void delete(int id) throws SQLException {
		logger.info("In deleteComputer method with id argument");
		
		String query = "DELETE FROM computer WHERE id = ?;";
		
		jdbcTemplate = new JdbcTemplate(connectionPool);

		jdbcTemplate.update(query, new Object[]{id});
		
		logger.info("Quit deleteComputer method");
	}

	public Computer read(int id) throws SQLException {
		logger.info("In findComputer method with id argument");
		
		String query = "SELECT  compu.id AS compu_id, compu.name AS compu_name, compu.introduced, compu.discontinued,  compa.name AS compa_name, compa.id AS compaID FROM computer AS compu LEFT OUTER JOIN company AS compa ON compu.company_id = compa.id WHERE compu.id = ?;";

		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		Computer computer = (Computer) jdbcTemplate.queryForObject(query.toString(), new Object[]{id},
				new RowMapper<Computer>(){
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Computer comput = Computer
						.builder()
						.id(rs.getInt("compu_id"))
						.name(rs.getString("compu_name"))
						.company(
								Company.builder().id(rs.getInt("compaID")).name(rs.getString("compa_name"))
										.build()).build();
				
				if(rs.getDate("introduced") != null)
					comput.setIntroducedDate(LocalDate.fromDateFields(rs.getDate("introduced")));
				
				if(rs.getDate("discontinued") != null)
					comput.setDiscontinuedDate(LocalDate.fromDateFields(rs.getDate("discontinued")));
				
				
				return comput;
			}
		});
		
		logger.info("Quit findComputer method");

		return computer;
	}

	public List<Computer> readSearch(Page page)
			throws SQLException {
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String sort = page.getSort();
		String ordre = page.getOrdre();
		String nom = page.getSearchName();
		
		logger.info("In searchComputer method");
		
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
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		List<Computer> computers = this.jdbcTemplate.query(query.toString(), new Object[]{"%" + nom + "%","%" + nom + "%"},
				new RowMapper<Computer>(){
			
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException{
				Computer computer = Computer
						.builder()
						.id(rs.getInt("compu_id"))
						.name(rs.getString("compu_name"))
						.company(
								Company.builder().id(rs.getInt("compaID")).name(rs.getString("compa_name"))
										.build()).build();
				
				if(rs.getDate("introduced") != null)
					computer.setIntroducedDate(LocalDate.fromDateFields(rs.getDate("introduced")));
				
				if(rs.getDate("discontinued") != null)
					computer.setDiscontinuedDate(LocalDate.fromDateFields(rs.getDate("discontinued")));
				
				
				return computer;
			}
			
		});
		
		logger.info("Quit searchComputer method");
		
		return computers;
	}
}