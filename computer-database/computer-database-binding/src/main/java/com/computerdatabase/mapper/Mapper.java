package com.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dto.ComputerDTO;

public class Mapper {

	public static Computer fromDTO(ComputerDTO computerDTO) {
		String introducedDate = computerDTO.getIntroducedDate();
		String discontinuedDate = computerDTO.getDiscontinuedDate();
		LocalDate dateIntroduced = null;
		LocalDate dateDiscontinued = null;

		String pattern = ResourceBundle.getBundle("message",
				LocaleContextHolder.getLocale()).getString("pattern.text");
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);

		if (introducedDate != null && introducedDate.compareTo("") != 0)
			dateIntroduced = formatter.parseLocalDate(introducedDate);

		if (discontinuedDate != null && discontinuedDate.compareTo("") != 0)
			dateDiscontinued = formatter.parseLocalDate(discontinuedDate);
		
		System.out.println("Id : "+computerDTO.getCompanyId()+" et nom : "+computerDTO.getCompanyName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		Company company = Company.builder().id(computerDTO.getCompanyId()).build();
		
		
		
		
		
		
		/*
		if(computerDTO.getCompanyId() == 0)
			company.setName("NULL");
		else
			company.setName(computerDTO.getCompanyName());
		*/
		
		Computer computer = Computer.builder().id(computerDTO.getId())
				.name(computerDTO.getName()).company(company)
				.introduced(dateIntroduced).discontinued(dateDiscontinued)
				.build();

		return computer;
	}

	public static ComputerDTO toDTO(Computer computer) {
		LocalDate introducedDate = computer.getIntroducedDate();
		LocalDate discontinuedDate = computer.getDiscontinuedDate();

		String pattern = ResourceBundle.getBundle("message",
				LocaleContextHolder.getLocale()).getString("pattern.text");
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);

		String dateIntroduced = (introducedDate != null) ? introducedDate
				.toString(formatter) : null;

		String dateDiscontinued = (discontinuedDate != null) ? discontinuedDate
				.toString(formatter) : null;

		ComputerDTO computerDTO = ComputerDTO.Builder().id(computer.getId())
				.name(computer.getName()).introducedDate(dateIntroduced)
				.discontinuedDate(dateDiscontinued)
				//.companyId(computer.getCompany().getId())
				//.companyName(computer.getCompany().getName())
				.build();
		
		if(computer.getCompany() == null)
			computerDTO.setCompanyId(0);
		else
		{
			computerDTO.setCompanyId(computer.getCompany().getId());
			computerDTO.setCompanyName(computer.getCompany().getName());
		}

		return computerDTO;
	}

	public static List<ComputerDTO> toDTO(List<Computer> computers) {
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();

		for (Computer computer : computers) {
			computersDTO.add(toDTO(computer));
		}

		return computersDTO;
	}

}
