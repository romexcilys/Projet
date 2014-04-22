package com.computerdatabase.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerdatabase.domain.Logs;

@Component
public class LogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void create(Logs log){
		
		log.setDate(DateTime.now());
		
		Session session = sessionFactory.getCurrentSession();
		session.save(log);
	}

}
