package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerdatabase.domain.Computer;
import com.computerdatabase.domain.Page;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class ListComputerServlet
 */
@WebServlet("/ListComputerServlet")
public class ListComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ComputerServices computerServices = ComputerServices
			.getInstance();

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
		
		
		int nombreElement = 20;
		int numeroPage = 1;

		session.setAttribute("search", false);

		String sort = "compu_name";
		if (request.getParameter("sort") != null
				&& request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");

		String ordre = "ASC";
		if (request.getParameter("ordre") != null
				&& request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");

		int nombreComputer = computerServices.getCount();
		int numberPage = (int) Math.ceil((double) nombreComputer
				/ nombreElement);
		
		try {

			if (request.getParameter("page") != null
					&& request.getParameter("page").compareTo("") != 0
					&& Integer.parseInt(request.getParameter("page")) >= 1
					&& Integer.parseInt(request.getParameter("page")) <= numberPage)
				numeroPage = Integer.parseInt(request.getParameter("page"));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("currentPage",
				numeroPage);

		numeroPage = (numeroPage - 1) * nombreElement;

		List<Computer> computers;
		
		Page page = Page.builder().currentPage(numeroPage).numberPage(numberPage).sort(sort).ordre(ordre).numberElement(nombreElement).build();

		computers = computerServices
				.get(page);
		
		//Ajouter a page computers
		
		request.setAttribute("computers", computers);
		request.setAttribute("number_page", numberPage);
		request.setAttribute("number_computer", nombreComputer);
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}
}
