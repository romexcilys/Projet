package com.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.computerdatabase.domain.Page;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class UpdateComputerServlet
 */
//@WebServlet("/UpdateComputerServlet")
@Controller
@RequestMapping("/PageDelete")
public class DeleteComputerServlet{

	
	@Autowired
	ComputerServices computerServices;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerServlet() {
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
		

		String sort = "compu_name";
		if (request.getParameter("sort") != null
				&& request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");

		String ordre = "asc";
		if (request.getParameter("ordre") != null
				&& request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");

		// CHOIX DE SUPPRESSION MULTIPLE
		if (request.getParameter("id") == null) {

			Page page = Page.builder().elementSearch(-1).numberElement(-1).sort(sort).ordre(ordre).build();
			
			computerServices.get(page);

			System.out.println(page.getComputers().size());
			
			request.setAttribute("infoPage", page);
			
			return new ModelAndView("delete");
			
			/*
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/delete.jsp")
					.forward(request, response);*/
		} else {

			int idComputer = Integer
					.parseInt(request.getParameter("id").trim());
			computerServices.delete(idComputer);

			return new ModelAndView("redirect:affichage?page=1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView fonctionPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String[] checkboxes = request.getParameterValues("idComputer");

		if (checkboxes != null) {
			for (int i = 0; i < checkboxes.length; i++) {
				int idComputer = Integer.parseInt(checkboxes[i]);
				computerServices.delete(idComputer);
			}
		}

		return new ModelAndView("redirect:affichage?page=1");
	}
}
