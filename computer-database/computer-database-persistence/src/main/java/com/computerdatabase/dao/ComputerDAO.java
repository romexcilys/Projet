package com.computerdatabase.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.wrapper.Page;

@Component
public class ComputerDAO {
	@PersistenceContext
	private EntityManager entityManager;

	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public void create(Computer computer) {
		logger.info("In insererComputer with computer argument");

		entityManager.persist(computer);
		entityManager.flush();

		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(Page page) {
		logger.info("In getListComputer with arguments");
		int debut = page.getElementSearch();
		int number = page.getNumberElement();

		String order = page.getSort() + " " + page.getOrdre();

		Query query = entityManager
				.createQuery(
						"select computer from Computer as computer left join computer.company as company order by "
								+ order).setFirstResult(debut)
				.setMaxResults(number);
		List<Computer> computers = (List<Computer>) query.getResultList();

		logger.info("Quit getListComputer method");

		return computers;
	}

	public int getNumber() {
		return ((Long) entityManager.createQuery(
				"select Count(*) from Computer").getSingleResult()).intValue();
	}

	public int getNumber(String nom) {
		return ((Long) entityManager
				.createQuery(
						"SELECT COUNT(*) FROM Computer AS computer LEFT JOIN computer.company as company WHERE LOWER(company.name) LIKE :compa OR LOWER(computer.name) LIKE :compa")
				.setParameter("compa", "%" + nom + "%").getSingleResult())
				.intValue();
	}

	public void update(Computer computer) {
		logger.info("In editComputer method");

		entityManager.merge(computer);

		logger.info("Quit editComputer method");
	}

	public void delete(int id) {
		logger.info("In deleteComputer method with id argument");

		Computer computer = entityManager.find(Computer.class, id);
		entityManager.remove(computer);
		
		logger.info("Quit deleteComputer method");
	}

	public Computer read(int id) {
		logger.info("In findComputer method with id argument");

		return entityManager.find(Computer.class, id);
	}

	public List<Computer> readSearch(Page page) {
		logger.info("In searchComputer method");
		
		int debut = page.getElementSearch();
		int number = page.getNumberElement();
		String nom = page.getSearchName();

		String order = page.getSort() + " " + page.getOrdre();
		
		Query query = entityManager
				.createQuery(
						"select computer from Computer as computer left join computer.company as company WHERE LOWER(company.name) LIKE :searchName OR LOWER(computer.name) LIKE :searchName order by "
								+ order).setParameter("searchName",  "%" + nom + "%").setFirstResult(debut)
				.setMaxResults(number);
		List<Computer> computers = (List<Computer>) query.getResultList();
		
		return computers;
	}
}