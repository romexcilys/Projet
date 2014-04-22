package com.computerdatabase.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.computerdatabase.orderspecifier.OrderSpecifierImpl;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.QCompany;
import com.computerdatabase.domain.QComputer;
import com.computerdatabase.wrapper.Page;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.path.PathBuilder;

@Component
public class ComputerDAO {
	@PersistenceContext
	private EntityManager entityManager;

	private final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	public void create(Computer computer) {
		logger.info("In insererComputer with computer argument");

		entityManager.persist(computer);

		logger.info("Quit insererComputer method");
	}

	public List<Computer> get(Page page) {
		logger.info("In getListComputer with arguments");
		int debut = page.getElementSearch();
		int number = page.getNumberElement();

		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPQLQuery query = new JPAQuery(entityManager);
		query.from(computer).leftJoin(computer.company, company).offset(debut)
				.limit(number);
		
		query.orderBy(OrderSpecifierImpl.retrieveOrderSpecification(page, company,
				computer));
		return query.list(computer);
	}

	public int getNumber() {
		QComputer computer = QComputer.computer;
		JPQLQuery query = new JPAQuery(entityManager);
		return query.from(computer).list(computer).size();
	}

	public int getNumber(String nom) {
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPQLQuery query = new JPAQuery(entityManager);

		return query
				.from(computer)
				.leftJoin(computer.company, company)
				.where(computer.name.like("%" + nom + "%").or(
						company.name.like("%" + nom + "%"))).list(computer)
				.size();

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

		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		JPQLQuery query = new JPAQuery(entityManager);
		query.from(computer)
				.leftJoin(computer.company, company)
				.offset(debut)
				.limit(number)
				.where(computer.name.like("%" + nom + "%").or(
						company.name.like("%" + nom + "%")));
		
		query.orderBy(OrderSpecifierImpl.retrieveOrderSpecification(page, company,
				computer));
		
		return query.list(computer);
	}

	
}