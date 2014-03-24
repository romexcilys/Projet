package com.computerdatabase.domain;

import java.util.List;

public class Page<T> {
	
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
	List<T> computers;
	String typeGene;
	
	
	
	public String getTypeGene() {
		return typeGene;
	}
	public void setTypeGene(String typeGene) {
		this.typeGene = typeGene;
	}
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
	public List<T> getComputers() {
		return computers;
	}
	public void setComputers(List<T> computers) {
		this.computers = computers;
	}


	public static class Builder<T>
	{
		Page<T> page;
		
		private Builder()
		{
			page = new Page<T>();
		}
		
		public Builder<T> id(int id)
		{
			this.page.id = id;
			return this;
		}
		
		public Builder<T> sort(String sort)
		{
			this.page.sort = sort;
			return this;
		}
		
		public Builder<T> ordre(String ordre)
		{
			this.page.ordre = ordre;
			return this;
		}
		
		public Builder<T> currentPage(int currentPage)
		{
			this.page.currentPage = currentPage;
			return this;
		}
		
		public Builder<T> numberPage(int numberPage)
		{
			this.page.numberPage = numberPage;
			return this;
		}
		
		public Builder<T> computers(List<T> computers)
		{
			this.page.computers = computers;
			return this;
		}
		
		public Builder<T> numberComputer(int numberComputer)
		{
			this.page.numberComputer = numberComputer;
			return this;
		}
		
		public Builder<T> name(String name)
		{
			this.page.name = name;
			return this;
		}
		public Builder<T> numberElement(int numberElement)
		{
			this.page.numberElement = numberElement;
			return this;
		}
		
		public Builder<T> searchName(String searchName)
		{
			this.page.searchName = searchName;
			return this;
		}
		
		public Builder<T> elementSearch(int elementSearch)
		{
			this.page.elementSearch = elementSearch;
			return this;
		}
		
		public Builder<T> typeGene(String typeGene)
		{
			this.page.typeGene = typeGene;
			return this;
		}
		
		public Page<T> build() {
			return this.page;
		}
		
	}
	
	public static <T>Builder<T> builder() {
		return new Builder<T>();
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
