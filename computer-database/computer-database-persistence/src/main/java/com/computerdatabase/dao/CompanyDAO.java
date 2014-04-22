package com.computerdatabase.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.computerdatabase.domain.Company;

@Repository
public class CompanyDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Company> read(){
		List<Company> companys = (List<Company>) entityManager.createQuery("from Company order by name").getResultList();
		return companys;
	}
}
