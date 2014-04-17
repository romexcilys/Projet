package com.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.computerdatabase.service.ComputerServices;
import com.computerdatabase.wrapper.Page;

/**
 * Servlet implementation class SearchComputerServlet
 */
@Controller
@RequestMapping("/SearchComputer")
public class SearchComputerController {

	@Autowired
	private ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchComputerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView fonctionGet(
			@RequestParam(value = "search", required = false) String nom,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "ordre", required = false) String ordre,
			@RequestParam(value = "page", required = false) Integer currentPage,
			HttpServletRequest request)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ModelAndView model = new ModelAndView("dashboard");

		final int nombreElement = 11;
		
		if(nom != null)
		{
			HttpSession session = request.getSession();
			session.setAttribute("search", true);
	
			if(currentPage == null)
				currentPage = 1;
	
			int elementSearch = (currentPage - 1) * nombreElement;
	
			Page  page = Page.builder().elementSearch(elementSearch)
					.currentPage(currentPage).numberElement(nombreElement)
					.sort(sort).ordre(ordre).name(nom)
					.searchName(nom.toLowerCase()).build();
			
			computerServices.find(page);
			
			int nombreComputer = page.getNumberComputer();
	
			int numberPage = (int) (Math.ceil((double) nombreComputer
					/ nombreElement));
	
			if (numberPage == 0)
				numberPage = 1;
	
			page.setNumberPage(numberPage);
	
			model.addObject("infoPage", page);
			return model;
		}
		else
			return new ModelAndView("redirect:affichage?page=1");

	}
}
