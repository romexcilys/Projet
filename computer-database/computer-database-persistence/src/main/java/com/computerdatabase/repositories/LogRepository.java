package com.computerdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.computerdatabase.domain.Logs;

public interface LogRepository extends JpaRepository<Logs, Long> {

}
