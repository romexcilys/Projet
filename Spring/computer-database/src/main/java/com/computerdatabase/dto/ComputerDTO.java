package com.computerdatabase.dto;

public class ComputerDTO {
	
	private int id;
	private String nom;
	private String introducedDate;
	private String discontinuedDate;
	private String companyName;
	private int companyId;
	
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
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
		
		public Builder companyName(String companyName)
		{
			this.computerDTO.companyName = companyName;
			return this;
		}
		
		public Builder companyId(int companyId)
		{
			this.computerDTO.companyId = companyId;
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
