package com.computerdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.computerdatabase.domain.Computer;

public interface ComputerRepository  extends JpaRepository<Computer, Long>{
	
	@Query("SELECT count(*) from Computer AS computer LEFT JOIN computer.company AS company WHERE LOWER(computer.name) LIKE LOWER(:search) OR LOWER(company.name) LIKE LOWER(:search)")
	int countSearch(@Param("search") String name);
	
	Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName,Pageable pageable);
}
