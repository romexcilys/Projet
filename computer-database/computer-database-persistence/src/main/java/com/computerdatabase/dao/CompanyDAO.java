package com.computerdatabase.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
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
/*
	@Autowired
	private EntityManager entityManager;
*/
	private final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	public List<Company> read() throws SQLException {
		logger.info("In getListCompany method");
		//sessionFactory = new AnnotationConfiguration().addAnnotatedClass(Company.class).configure().buildSessionFactory();
		/*Query query = entityManager..createQuery("from Company order by name");

		List<Company> companys = (List<Company>) query.list();*/
		
		  
		  
		  jdbcTemplate = new JdbcTemplate(connectionPool);
		  
		  List<Company> companys =
		  this.jdbcTemplate.query("select * from company order by name", new
		  BeanPropertyRowMapper<Company>(Company.class));
		 
		logger.info("Quit getListCompany method");

		return companys;
	}

}
