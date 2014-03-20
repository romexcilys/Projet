package com.computerdatabase.validator;

public interface Validator {
	
	public boolean verifierDate(String date);
	public boolean verifierName(String name);
	public boolean comparerDate(String date1, String date2);
	
}
