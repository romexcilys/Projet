package com.company.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.Computer;
import com.company.dao.ComputerDAO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom = request.getParameter("search").toLowerCase();
		
		if(nom.length() != 0)
		{
			List<Computer> computers = ComputerDAO.getInstance().searchComputer(nom);
			
			
			request.setAttribute("computers", computers);
			request.setAttribute("number_computer", computers.size());
			
			this.getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
		else
			response.sendRedirect("affichage");
		//SINON NE PAS METTRE LE RETOUR A AFFICHAGE POSSIBLE ET CHERCHE TOUT CEUX QUI N'ONT PAS DE COMPANY
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
