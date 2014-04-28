package com.computerdatabase.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.service.ComputerServices;

@Service("ComputerWebService")
public class ComputerWebServiceImpl implements ComputerWebService{
	
	@Autowired
	private ComputerServices computerServices;
	
	public List<String> computerList()
	{
		List<String> liste = new ArrayList<String>();
		List<Computer> computers = computerServices.findAll();
		
		for(Computer computer : computers)
		{
			liste.add(computer.toString());
		}
		return liste;
	}
	/*
	public String sayHelloWorld()
	{
		return "Hello world";
	}
	*/
}
