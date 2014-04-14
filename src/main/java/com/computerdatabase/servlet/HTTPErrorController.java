package com.computerdatabase.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTTPErrorController {

	@RequestMapping(value="/errors")
	public ModelAndView handle404(){
		ModelAndView model = new ModelAndView("error404");
		
		return model;
	}
	/*
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
 
		ModelAndView model = new ModelAndView("error404");
		return model;
 
	}*/
	
}
