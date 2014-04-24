package com.computerdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.computerdatabase.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
