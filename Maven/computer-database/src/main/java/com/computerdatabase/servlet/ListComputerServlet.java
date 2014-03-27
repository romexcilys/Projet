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
 * Servlet implementation class ListComputerServlet
 */
@WebServlet("/ListComputerServlet")
public class ListComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		Page page = Page.builder().elementSearch(elementSearch).currentPage(currentPage).sort(sort).ordre(ordre).numberElement(nombreElement).build();
		
		ComputerServices.INSTANCE.get(page);
		
		int nombreComputer = page.getNumberComputer();
		
		int numberPage = (int) Math.ceil((double) nombreComputer
				/ nombreElement);
		
		page.setNumberPage(numberPage);
		
		
		request.setAttribute("infoPage", page);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}
}
