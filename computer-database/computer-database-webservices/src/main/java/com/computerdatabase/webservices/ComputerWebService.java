package com.computerdatabase.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ComputerWebService {
	
	@WebMethod
	public List<String> computerList();
	/*
	@WebMethod
	public String sayHelloWorld();*/
}
