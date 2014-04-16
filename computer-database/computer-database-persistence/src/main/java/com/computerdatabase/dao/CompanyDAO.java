package com.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.computerdatabase.domain.Company;

@Repository
public class CompanyDAO {
	
	
	@Autowired
	private DataSource connectionPool;
	
	private JdbcTemplate jdbcTemplate;

	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	public List<Company> get() throws SQLException {
		logger.info("In getListCompany method");
		
		jdbcTemplate = new JdbcTemplate(connectionPool);
		
		List<Company> companys = this.jdbcTemplate.query("select * from company order by name",
				new BeanPropertyRowMapper<Company>(Company.class));
		/*
		List<Company> companys = this.jdbcTemplate.query("select * from company order by name",
				new RowMapper<Company>(){
			
			public Company mapRow(ResultSet rs, int rowNum) throws SQLException{
				Company company = Company.builder().id(rs.getInt("id")).name(rs.getString("name")).build();
				return company;
			}
			
		});*/
		
		logger.info("Quit getListCompany method");

		return companys;
	}

}
