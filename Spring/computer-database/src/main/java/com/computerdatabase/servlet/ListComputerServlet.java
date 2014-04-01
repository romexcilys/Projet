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
import org.springframework.web.servlet.ModelAndView;

import com.computerdatabase.domain.Page;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class ListComputerServlet
 */
//@WebServlet("/ListComputerServlet")
@Controller
@RequestMapping("/affichage")
public class ListComputerServlet{
	
	@Autowired
	ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView fonctionGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("cocuou");
		HttpSession session = request.getSession();


		final int nombreElement = 11;
		int currentPage = 1;

		session.setAttribute("search", false);

		String sort = "compu_name";
		if (request.getParameter("sort") != null
				&& request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");

		String ordre = "ASC";
		if (request.getParameter("ordre") != null
				&& request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");

		try {

			if (request.getParameter("page") != null
					&& request.getParameter("page").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("page")) >= 1)
				currentPage = Integer.parseInt(request.getParameter("page"));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int elementSearch = (currentPage - 1) * nombreElement;

		Page page = Page.builder().elementSearch(elementSearch)
				.currentPage(currentPage).sort(sort).ordre(ordre)
				.numberElement(nombreElement).build();

		computerServices.get(page);

		int nombreComputer = page.getNumberComputer();

		int numberPage = (int) Math.ceil((double) nombreComputer
				/ nombreElement);

		page.setNumberPage(numberPage);

		request.setAttribute("infoPage", page);

		
		System.out.println("I'm here");
		
		return new ModelAndView("dashboard");
	}
}
