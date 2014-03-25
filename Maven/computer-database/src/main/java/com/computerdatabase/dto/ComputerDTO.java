package com.computerdatabase.dto;

import com.computerdatabase.domain.Company;


public class ComputerDTO {
	
	private int id;
	private String nom;
	private String introducedDate;
	private String discontinuedDate;
	private Company company;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getIntroducedDate() {
		return introducedDate;
	}
	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}
	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}


	public static class Builder
	{
		ComputerDTO computerDTO;
		
		public Builder()
		{
			computerDTO = new ComputerDTO();
		}
		
		public Builder id(int id)
		{
			this.computerDTO.id = id;
			return this;
		}
		
		public Builder nom(String nom)
		{
			this.computerDTO.nom = nom;
			return this;
		}
		
		public Builder introducedDate(String introducedDate)
		{
			this.computerDTO.introducedDate = introducedDate;
			return this;
		}
		
		public Builder discontinuedDate(String discontinuedDate)
		{
			this.computerDTO.discontinuedDate = discontinuedDate;
			return this;
		}
		
		public Builder company(Company company)
		{
			this.computerDTO.company = company;
			return this;
		}
		
		public ComputerDTO build()
		{
			return this.computerDTO;
		}
		
	}

	public static Builder Builder()
	{
		return new Builder();
	}
	
}
