package com.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.computerdatabase.domain.Page;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class UpdateComputerServlet
 */
@WebServlet("/UpdateComputerServlet")
public class DeleteComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Configuration.xml");	
		ComputerServices computerServices = (ComputerServices) applicationContext.getBean("computerServices");

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
			
			((ClassPathXmlApplicationContext)applicationContext).close();
			
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/delete.jsp")
					.forward(request, response);
		} else {

			int idComputer = Integer
					.parseInt(request.getParameter("id").trim());
			computerServices.delete(idComputer);

			((ClassPathXmlApplicationContext)applicationContext).close();

			response.sendRedirect("affichage?page=1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Configuration.xml");	
		ComputerServices computerServices = (ComputerServices) applicationContext.getBean("computerServices");

		String[] checkboxes = request.getParameterValues("idComputer");

		if (checkboxes != null) {
			for (int i = 0; i < checkboxes.length; i++) {
				int idComputer = Integer.parseInt(checkboxes[i]);
				computerServices.delete(idComputer);
			}
		}

		((ClassPathXmlApplicationContext)applicationContext).close();
		response.sendRedirect("affichage?page=1");
	}
}
