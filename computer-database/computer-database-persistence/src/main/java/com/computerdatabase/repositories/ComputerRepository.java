package com.computerdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.computerdatabase.domain.Computer;

public interface ComputerRepository  extends JpaRepository<Computer, Long>{
	
	Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName,Pageable pageable);
}
