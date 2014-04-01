package com.computerdatabase.domain;


import org.joda.time.LocalDate;

public class Computer {

	private int id;
	
	private String name;
	
	private LocalDate introducedDate;
	
	private LocalDate discontinuedDate;
	private Company company;

	public Computer() {
		id = 0;
		name = null;
		introducedDate = null;
		discontinuedDate = null;
		company = null;
	}

	public Computer(int id, String name, LocalDate introducedDate,
			LocalDate discontinuedDate, Company company) {
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
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
			this.computer.name = name;
			return this;
		}

		public Builder introduced(LocalDate introduced) {
			this.computer.introducedDate = introduced;
			return this;
		}

		public Builder discontinued(LocalDate discontinued) {
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
		return "Computer [id=" + id + ", nom=" + name + ", introducedDate="
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
