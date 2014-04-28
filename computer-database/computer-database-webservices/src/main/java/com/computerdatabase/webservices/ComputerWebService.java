package com.computerdatabase.webservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes("application/json")
@Produces("application/json")
public interface ComputerWebService {
	
	@POST
	@Path("/computerList")
	public List<String> computerList();
/*
	@POST
	@Path("/hello")
	public String sayHelloWorld();
	*/
}
