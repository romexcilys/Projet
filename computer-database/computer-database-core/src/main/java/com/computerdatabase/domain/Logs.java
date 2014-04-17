package com.computerdatabase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Logs {
	
	@Column(name="operation")
	private String operation;
	
	@Column(name="idComputer")
	private int idComputer;
	
	@Column(name="name")
	private String name;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getIdComputer() {
		return idComputer;
	}
	public void setIdComputer(int idComputer) {
		this.idComputer = idComputer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static class Builder{
		Logs log;
		
		public Builder()
		{
			log = new Logs();
		}
		
		public Builder operation(String operation)
		{
			this.log.operation = operation;
			return this;
		}
		
		public Builder idComputer(int idComputer)
		{
			this.log.idComputer = idComputer;
			return this;
		}
		
		public Builder name(String name)
		{
			this.log.name = name;
			return this;
		}
		
		public Logs build()
		{
			return this.log;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
