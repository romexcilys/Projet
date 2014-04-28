package com.computerdatabase.webservices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.service.ComputerServices;
import com.computerdatabase.wrapper.PageWrapper;

@Service("ComputerWebService")
public class ComputerWebServiceImpl implements ComputerWebService{
	
	@Autowired
	private ComputerServices computerServices;
	
	public List<String> computerList()
	{
		List<String> liste = new ArrayList<String>();
		List<Computer> computers = computerServices.get(PageWrapper.builder().numberElement(11).currentPage(0).build());
		
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
