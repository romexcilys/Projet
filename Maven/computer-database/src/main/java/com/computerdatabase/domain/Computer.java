package com.computerdatabase.domain;

import java.util.Date;

public class Computer {

	private int id;
	private String nom;
	private Date introducedDate;
	private Date discontinuedDate;
	private Company company;

	public Computer() {
		id = 0;
		nom = null;
		introducedDate = null;
		discontinuedDate = null;
		company = null;
	}

	public Computer(int id, String nom, Date introducedDate,
			Date discontinuedDate, Company company) {
		this.id = id;
		this.nom = nom;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		this.company = company;
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

	public Date getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(Date introducedDate) {
		this.introducedDate = introducedDate;
	}

	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static class Builder {

		Computer computer;

		private Builder() {
			computer = new Computer();
		}

		public Builder id(int id) {
			this.computer.id = id;
			return this;
		}

		public Builder name(String name) {
			this.computer.nom = name;
			return this;
		}

		public Builder introduced(Date introduced) {
			this.computer.introducedDate = introduced;
			return this;
		}

		public Builder discontinued(Date discontinued) {
			this.computer.discontinuedDate = discontinued;
			return this;
		}

		public Builder company(Company company) {
			this.computer.company = company;
			return this;
		}

		public Computer build() {
			return this.computer;
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", nom=" + nom + ", introducedDate="
				+ introducedDate + ", discontinuedDate=" + discontinuedDate
				+ ", company=" + company + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
