package com.company.bean;

import java.util.Date;

public class Computer {
	
	private String nom ;
	private Date introduced_date;
	private Date discontinued_date;
	private int company;
	private String company_name;
	
	public Computer(String nom, Date introduced_date, Date discontinued_date,
			int company, String company_name) {
		this.nom = nom;
		this.introduced_date = introduced_date;
		this.discontinued_date = discontinued_date;
		this.company = company;
		this.company_name = company_name;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getIntroduced_date() {
		return introduced_date;
	}

	public void setIntroduced_date(Date introduced_date) {
		this.introduced_date = introduced_date;
	}

	public Date getDiscontinued_date() {
		return discontinued_date;
	}

	public void setDiscontinued_date(Date discontinued_date) {
		this.discontinued_date = discontinued_date;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	@Override
	public String toString() {
		return "Computer [nom=" + nom + ", introduced_date=" + introduced_date
				+ ", discontinued_date=" + discontinued_date + ", company="
				+ company + ", company_name=" + company_name + "]";
	}
	
	
	

}
