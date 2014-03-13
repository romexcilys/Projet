package com.computerdatabase.domain;


public class Company {
	
	private int id;
	private String nom;
	
	public Company()
	{
		id = 0;
		nom = null;
	}
	
	public Company(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

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

	@Override
	public String toString() {
		return "Company [id=" + id + ", nom=" + nom + "]";
	}
	
	public static class Builder {

		Company company;

		private Builder() {
			company = new Company();
		}

		public Builder id(int id) {
			this.company.id = id;
			return this;
		}
		
		public Builder nom(String nom)
		{
			this.company.nom = nom;
			return this;
		}

		public Company build() {
			return this.company;
		}

	}

	public static Builder builder() {
		return new Builder();
	}
	
}
