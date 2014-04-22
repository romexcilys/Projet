package com.computerdatabase.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.QCompany;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class CompanyDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Company> read(){
		QCompany company = QCompany.company;
		JPQLQuery query = new JPAQuery (entityManager);
		
		return query.from(company).orderBy(company.name.asc()).list(company);
	}
}
