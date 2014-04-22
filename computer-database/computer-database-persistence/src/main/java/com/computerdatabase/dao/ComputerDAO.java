package com.computerdatabase.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.wrapper.Page;

@Component
public class ComputerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public void create(Computer computer) {
		logger.info("In insererComputer with computer argument");

		Session session = sessionFactory.getCurrentSession();
		session.save(computer);
		
		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(Page page) {
		logger.info("In getListComputer with arguments");
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		
		Session session = sessionFactory.getCurrentSession();
		
		String order = page.getSort() + " " + page.getOrdre();
		
		Query query = session
				.createQuery(
						"select computer from Computer as computer left join computer.company as company order by "
								+ order).setFirstResult(debut)
				.setMaxResults(number);
		List<Computer> computers = (List<Computer>) query.list();

		logger.info("Quit getListComputer method");

		return computers;
	}

	public int getNumber() {
		Session session = sessionFactory.getCurrentSession();
		return ((Long) session.createQuery(
				"select Count(*) from Computer").uniqueResult()).intValue();
	}

	public int getNumber(String nom) {
		Session session = sessionFactory.getCurrentSession();
		return ((Long) session
				.createQuery(
						"SELECT COUNT(*) FROM Computer AS computer LEFT JOIN computer.company as company WHERE LOWER(company.name) LIKE :compa OR LOWER(computer.name) LIKE :compa")
				.setParameter("compa", "%" + nom + "%").uniqueResult())
				.intValue();
	}

	public void update(Computer computer) {
		logger.info("In editComputer method");

		Session session = sessionFactory.getCurrentSession();
		session.merge(computer);

		logger.info("Quit editComputer method");
	}

	public void delete(int id) {
		logger.info("In deleteComputer method with id argument");
		
		Session session = sessionFactory.getCurrentSession();

		Computer computer = (Computer) session.get(Computer.class, id);
		session.delete(computer);
		
		logger.info("Quit deleteComputer method");
	}

	public Computer read(int id) {
		logger.info("In findComputer method with id argument");
		Session session = sessionFactory.getCurrentSession();
		return (Computer) session.get(Computer.class, id);
	}

	public List<Computer> readSearch(Page page) {
		logger.info("In searchComputer method");
		
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String nom = page.getSearchName();
		
		Session session = sessionFactory.getCurrentSession();
		
		String order = page.getSort() + " " + page.getOrdre();
		
		Query query = session
				.createQuery(
						"select computer from Computer as computer left join computer.company as company WHERE LOWER(company.name) LIKE :searchName OR LOWER(computer.name) LIKE :searchName order by "
								+ order).setParameter("searchName",  "%" + nom + "%").setFirstResult(debut)
				.setMaxResults(number);
		List<Computer> computers = (List<Computer>) query.list();
		
		return computers;
	}
}