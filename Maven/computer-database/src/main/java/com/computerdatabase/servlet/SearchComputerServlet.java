package com.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerdatabase.domain.Page;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class SearchComputerServlet
 */
@WebServlet("/SearchComputerServlet")
public class SearchComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final int nombreElement = 11;
		
		String nom = "";
		if (request.getParameter("search") != null)
			nom = request.getParameter("search").toLowerCase();

		HttpSession session = request.getSession();

		String sort = "compu_name";
		if (request.getParameter("sort") != null
				&& request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");

		String ordre = "ASC";
		if (request.getParameter("ordre") != null
				&& request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");

		// EST CE QUE LA VALEUR DE RECHERCHE N'EST PAS VIDE
		if (nom.length() != 0) {

			session.setAttribute("search", true);

			int currentPage = 1;
			try{
				
				if (request.getParameter("page") != null)
				currentPage = Integer.parseInt(request.getParameter("page"));
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int elementSearch = (currentPage - 1) * nombreElement;

			Page page = Page.builder().elementSearch(elementSearch)
					.currentPage(currentPage).numberElement(nombreElement)
					.sort(sort).ordre(ordre).name(nom).searchName(nom)
					.build();

			ComputerServices
			.INSTANCE.find(page);

			int nombreComputer = page.getNumberComputer();
			
			int numberPage = (int) (Math.ceil((double) nombreComputer
					/ nombreElement));
			
			if(numberPage == 0)
				numberPage = 1;
			
			page.setNumberPage(numberPage);

			request.setAttribute("infoPage", page);

			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/dashboard.jsp")
					.forward(request, response);

		} else// SI STRING VIDE ALORS ON REVIENT SUR PAGE AFFICHAGE
		{
			session.setAttribute("search", false);
			response.sendRedirect("affichage?page=1");
		}
	}
}
