package com.computerdatabase.wrapper;

import java.util.List;

import com.computerdatabase.dto.ComputerDTO;

public class PageWrapper {
	
	int id;
	String sort;
	String ordre;
	int currentPage;
	int elementSearch;
	int numberPage;
	int numberComputer;
	int numberElement;
	String searchName;
	String name;
	List<ComputerDTO> computers;
	
	
	public int getElementSearch() {
		return elementSearch;
	}
	public void setElementSearch(int elementSearch) {
		this.elementSearch = elementSearch;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public int getNumberElement() {
		return numberElement;
	}
	public void setNumberElement(int numberElement) {
		this.numberElement = numberElement;
	}
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
	public List<ComputerDTO> getComputers() {
		return computers;
	}
	public void setComputers(List<ComputerDTO> computers) {
		this.computers = computers;
	}


	public static class Builder
	{
		PageWrapper page;
		
		private Builder()
		{
			page = new PageWrapper();
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
		
		public Builder computers(List<ComputerDTO> computers)
		{
			this.page.computers = computers;
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
		public Builder numberElement(int numberElement)
		{
			this.page.numberElement = numberElement;
			return this;
		}
		
		public Builder searchName(String searchName)
		{
			this.page.searchName = searchName;
			return this;
		}
		
		public Builder elementSearch(int elementSearch)
		{
			this.page.elementSearch = elementSearch;
			return this;
		}
		
		
		public PageWrapper build() {
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
