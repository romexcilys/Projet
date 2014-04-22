package com.computerdatabase.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.computerdatabase.domain.Company;

@Repository
public class CompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Company> read(){
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Company.class).addOrder(Order.asc("name")).list();
	}
}
