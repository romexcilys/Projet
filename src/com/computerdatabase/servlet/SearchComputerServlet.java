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
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class SearchComputerServlet
 */
@WebServlet("/SearchComputerServlet")
public class SearchComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ComputerServices computerServices = ComputerServices.getInstance();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SearchComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom = request.getParameter("search").toLowerCase();
		HttpSession session = request.getSession();
		
		String sort = "compu.name";
		if(request.getParameter("sort") != null && request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");
		
		String ordre = "ASC";
		if(request.getParameter("ordre") != null && request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");
		
		//session.setAttribute("choixPage", false);
		
		if(request.getParameter("page") != null)
			session.setAttribute("choixPage", true);
		else
			session.setAttribute("choixPage", false);
		
		//EST CE QUE LA VALEUR DE RECHERCHE N'EST PAS VIDE
		if(nom.length() != 0)
		{
			session.setAttribute("search", true);
			int nombre = computerServices.getCount( nom );
			request.setAttribute("number_computer", nombre);
			
			
			if(session.getAttribute("choixPage") != null)
			{
				//SI DEMANDE DE PAGINATION
				if((Boolean)session.getAttribute("choixPage"))
				{
					//response.sendRedirect("affichage?page=1");
					
					int nombreElement = -1;
					int numeroPage = 0;
					
					
					nombreElement = 20;
					int nombreComputer = nombre;
					int numberPage = (int) (Math.ceil((double)nombreComputer/nombreElement));
					session.setAttribute("numberPage", numberPage);
					request.setAttribute("currentPage", Integer.parseInt(request.getParameter("page")));
					session.setAttribute("choixPage", true);
					
					numeroPage = (Integer.parseInt(request.getParameter("page"))-1)*nombreElement;
					
					List<Computer> computers;
					
					computers = computerServices.find(nom,numeroPage,nombreElement, sort, ordre);
					
					request.setAttribute("computers", computers);
					
				}//PAS DE PAGINATION
				else
				{
					List<Computer> computers = computerServices.find(nom, sort, ordre);
					
					request.setAttribute("computers", computers);
					
				}
			}
			else//PAS DE VARIABLE SESSION SUR LA PAGINATION
			{
				List<Computer> computers = computerServices.find(nom, sort, ordre);
				
				request.setAttribute("computers", computers);
			}
			
			
			request.setAttribute("searchName", nom);
			this.getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
			
		}
		else//SI STRING VIDE ALORS ON REVIENT SUR PAGE AFFICHAGE
		{
			if(session.getAttribute("choixPage") != null && (Boolean)session.getAttribute("choixPage") == true )
			{
				if((Boolean)session.getAttribute("choixPage"))
				{
					response.sendRedirect("affichage?page=1");
				}
				else
					response.sendRedirect("affichage");
			}
		}
	}
}
