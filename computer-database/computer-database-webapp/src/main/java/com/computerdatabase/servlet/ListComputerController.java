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
import com.computerdatabase.wrapper.PageWrapper;

/**
 * Servlet implementation class ListComputerServlet
 */
@Controller
@RequestMapping("/affichage")
public class ListComputerController{
	
	@Autowired
	private ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListComputerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView fonctionGet(@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "ordre", required = false) String ordre, @RequestParam(value = "page", required = false) Integer currentPage, HttpServletRequest request
			) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("dashboard");

		final int nombreElement = 11;

		session.setAttribute("search", false);

		if(currentPage == null)
			currentPage = 1;

		int elementSearch = (currentPage - 1) * nombreElement;

		PageWrapper  page = PageWrapper.builder().elementSearch(elementSearch)
				.currentPage(currentPage).sort(sort).ordre(ordre)
				.numberElement(nombreElement).build();

		computerServices.get(page);
		
		int nombreComputer = page.getNumberComputer();

		int numberPage = (int) Math.ceil((double) nombreComputer
				/ nombreElement);

		page.setNumberPage(numberPage);
		
		model.addObject("infoPage", page);

		return model;
	}
}
