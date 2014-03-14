package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.domain.Computer;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		//CHOIX DE SUPPRESSION MULTIPLE
		if(request.getParameter("id") == null)
		{
		
			List<Computer> computers;
			
			computers = ComputerDAO.getInstance().getListComputer();
			
			//int nombre = computerDao.getNumberComputer();
			
			request.setAttribute("computers", computers);
			request.setAttribute("number_computer", computers.size());
			
			this.getServletContext().getRequestDispatcher("/delete.jsp").forward(request, response);
		}
		else
		{
			
				
			int idComputer = Integer.parseInt(request.getParameter("id").trim());
			ComputerDAO.getInstance().deleteComputer(idComputer);
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
				ComputerDAO.getInstance().deleteComputer(idComputer);
			}
			
		}
		
		
		response.sendRedirect("affichage");
	}
}
