package com.computerdatabase.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;

public class Mapper {
	
	public static Computer mapper(ComputerDTO computerDTO)
	{
		int id = computerDTO.getId();
		String name = computerDTO.getNom();
		String introducedDate = computerDTO.getIntroducedDate();
		String discontinuedDate = computerDTO.getDiscontinuedDate();
		int idCompany = computerDTO.getIdCompany();
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIntroduced = null;
		Date dateDiscontinued = null;
		
		if (introducedDate != null) {
			try {
				dateIntroduced = (Date) sdf.parse(introducedDate);
				System.out.println(dateIntroduced);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				dateIntroduced = (Date) sdf.parse("0000-00-00");
				System.out.println(dateIntroduced);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (discontinuedDate != null) {
			try {
				dateDiscontinued = (Date) sdf.parse(discontinuedDate);
				System.out.println(dateDiscontinued);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				dateDiscontinued = (Date) sdf.parse("0000-00-00");
				System.out.println(dateDiscontinued);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Company company = Company.builder().id(idCompany).build();
		Computer computer = Computer.builder().id(id).name(name).company(company).introduced(dateIntroduced).discontinued(dateDiscontinued).build();
		
		return computer;
	}

}