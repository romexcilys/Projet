package com.computerdatabase.domain;

import java.util.List;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Company.Builder;

public class Page {
	
	int id;
	String sort;
	String ordre;
	int currentPage;
	int numberPage;
	int numberComputer;
	String name;
	List<Computer> computers;
	List<Company> companys; //Pas sur si interet
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberComputer() {
		return numberComputer;
	}
	public void setNumberComputer(int numberComputer) {
		this.numberComputer = numberComputer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrdre() {
		return ordre;
	}
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumberPage() {
		return numberPage;
	}
	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}
	public List<Computer> getComputers() {
		return computers;
	}
	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
	
	public List<Company> getCompanys() {
		return companys;
	}
	public void setCompanys(List<Company> companys) {
		this.companys = companys;
	}



	public static class Builder
	{
		Page page;
		
		private Builder()
		{
			page = new Page();
		}
		
		public Builder id(int id)
		{
			this.page.id = id;
			return this;
		}
		
		public Builder sort(String sort)
		{
			this.page.sort = sort;
			return this;
		}
		
		public Builder ordre(String ordre)
		{
			this.page.ordre = ordre;
			return this;
		}
		
		public Builder currentPage(int currentPage)
		{
			this.page.currentPage = currentPage;
			return this;
		}
		
		public Builder numberPage(int numberPage)
		{
			this.page.numberPage = numberPage;
			return this;
		}
		
		public Builder computers(List<Computer> computers)
		{
			this.page.computers = computers;
			return this;
		}
		
		public Builder companys(List<Company> companys)
		{
			this.page.companys = companys;
			return this;
		}
		
		public Builder numberComputer(int numberComputer)
		{
			this.page.numberComputer = numberComputer;
			return this;
		}
		
		public Builder name(String name)
		{
			this.page.name = name;
			return this;
		}
		
		public Page build() {
			return this.page;
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
