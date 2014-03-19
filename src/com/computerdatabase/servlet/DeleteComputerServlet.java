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
 * Servlet implementation class UpdateComputerServlet
 */
@WebServlet("/UpdateComputerServlet")
public class DeleteComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ComputerServices computerServices = ComputerServices.getInstance();
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		

		String sort = "compu_name";
		if(request.getParameter("sort") != null && request.getParameter("sort").compareTo("") != 0)
			sort = request.getParameter("sort");
		
		String ordre = "asc";
		if(request.getParameter("ordre") != null && request.getParameter("ordre").compareTo("") != 0)
			ordre = request.getParameter("ordre");
		
		
		//CHOIX DE SUPPRESSION MULTIPLE
		if(request.getParameter("id") == null)
		{
		
			List<Computer> computers;
			
			computers = computerServices.get(sort, ordre);
			
			//int nombre = computerDao.getNumberComputer();
			
			request.setAttribute("computers", computers);
			request.setAttribute("number_computer", computers.size());
			
			this.getServletContext().getRequestDispatcher("/delete.jsp").forward(request, response);
		}
		else
		{
			
			int idComputer = Integer.parseInt(request.getParameter("id").trim());
			computerServices.delete(idComputer);
			
			
			if(session.getAttribute("choixPage") != null && (Boolean) session.getAttribute("choixPage") == true)
				response.sendRedirect("affichage?page=1");
			else
				response.sendRedirect("affichage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] checkboxes = request.getParameterValues("idComputer");
		
		if(checkboxes != null)
		{
			for(int i = 0; i < checkboxes.length; i++)
			{
				int idComputer = Integer.parseInt(checkboxes[i]);
				computerServices.delete(idComputer);
			}
		}
		
		response.sendRedirect("affichage");
	}
}
