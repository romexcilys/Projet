package com.computerdatabase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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
		Criteria criteria = session.createCriteria(Computer.class)
				.setFirstResult(debut).setMaxResults(number)
				.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN);

		if (page.getOrdre().equals("desc"))
			criteria.addOrder(Order.desc(page.getSort()));
		else
			criteria.addOrder(Order.asc(page.getSort()));

		return criteria.list();
	}

	public int getNumber() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Computer.class)
				.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN)
				.list().size();
	}

	public int getNumber(String nom) {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createCriteria(Computer.class)
				.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.or(
						Restrictions.like("company.name", "%" + nom + "%"),
						Restrictions.like("name", "%" + nom + "%"))).list()
				.size();
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

		Criteria criteria = session
				.createCriteria(Computer.class)
				.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN)
				.setFirstResult(debut)
				.setMaxResults(number)
				.add(Restrictions.or(
						Restrictions.like("company.name", "%" + nom + "%"),
						Restrictions.like("name", "%" + nom + "%")));

		if (page.getOrdre().equals("desc"))
			criteria.addOrder(Order.desc(page.getSort()));
		else
			criteria.addOrder(Order.asc(page.getSort()));
		
		return criteria.list();
	}
}