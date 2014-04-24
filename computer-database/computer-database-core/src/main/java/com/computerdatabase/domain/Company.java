package com.computerdatabase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@Column(name = "id",nullable = true)
	@GeneratedValue
	private long id;
	
	@Column(name = "name",nullable = true)
	private String name;
	
	public Company()
	{
		id = 0;
		name = null;
	}
	
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", nom=" + name + "]";
	}
	
	public static class Builder {

		Company company;

		private Builder() {
			company = new Company();
		}

		public Builder id(long id) {
			this.company.id = id;
			return this;
		}
		
		public Builder name(String name)
		{
			this.company.name = name;
			return this;
		}

		public Company build() {
			return this.company;
		}
	}

	public static Builder builder() {
		return new Builder();
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
