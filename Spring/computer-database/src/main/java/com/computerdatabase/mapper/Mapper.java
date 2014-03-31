package com.computerdatabase.mapper;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dto.ComputerDTO;

public class Mapper {
	
	public static Computer fromDTO(ComputerDTO computerDTO)
	{
		int id = computerDTO.getId();
		String name = computerDTO.getNom();
		String introducedDate = computerDTO.getIntroducedDate();
		String discontinuedDate = computerDTO.getDiscontinuedDate();
		int idCompany = computerDTO.getCompanyId();
		String nomCompany = computerDTO.getCompanyName();
		
		
		LocalDate dateIntroduced = null;
		LocalDate dateDiscontinued = null;
		
		if (introducedDate != null) {
			dateIntroduced = new LocalDate(introducedDate);
			System.out.println(dateIntroduced);
		} 

		if (discontinuedDate != null) {
			dateDiscontinued = new LocalDate(discontinuedDate);
			System.out.println(dateDiscontinued);
		}
		
		Company company = Company.builder().id(idCompany).nom(nomCompany).build();
		Computer computer = Computer.builder().id(id).name(name).company(company).introduced(dateIntroduced).discontinued(dateDiscontinued).build();
		
		return computer;
	}
	
	
	public static ComputerDTO toDTO(Computer computer)
	{
		int id = computer.getId();
		String nom = computer.getNom();
		LocalDate introducedDate = computer.getIntroducedDate();
		LocalDate discontinuedDate = computer.getDiscontinuedDate();
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		String dateIntroduced = null;
		if(introducedDate != null)
			dateIntroduced = formatter.print(introducedDate); 
		
		String dateDiscontinued = null;
		if(discontinuedDate != null)
			dateDiscontinued = formatter.print(discontinuedDate); 
		
		
		
		ComputerDTO computerDTO = ComputerDTO.Builder().id(id).nom(nom).introducedDate(dateIntroduced).discontinuedDate(dateDiscontinued).companyId(computer.getCompany().getId()).companyName(computer.getCompany().getNom()).build();
		
		
		return computerDTO;
	}

}