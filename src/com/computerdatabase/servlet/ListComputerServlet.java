package com.computerdatabase.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerdatabase.dao.ConnectionManager;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class ListComputerServlet
 */
@WebServlet("/ListComputerServlet")
public class ListComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ComputerServices computerServices = new ComputerServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int nombreElement = -1;
		int numeroPage = 0;
		
		session.setAttribute("search", false);
		
		Connection connection = ConnectionManager.getConnection();
		
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(session.getAttribute("numberPage") == null)
		{
			nombreElement = 20;
			int nombreComputer = computerServices.getCount(connection);
			int numberPage = (int) Math.ceil((double)nombreComputer/nombreElement);
			session.setAttribute("numberPage", numberPage);
		}
		try {
			
			if(request.getParameter("page") != null && request.getParameter("page").compareTo("") != 0 && Integer.parseInt(request.getParameter("page")) >= 1  && Integer.parseInt(request.getParameter("page")) <= (Integer)session.getAttribute("numberPage"))
			{
				nombreElement = 20;
				int nombreComputer = computerServices.getCount(connection);
				int numberPage = (int) (Math.ceil((double)nombreComputer/nombreElement));
				session.setAttribute("numberPage", numberPage);
				request.setAttribute("currentPage", Integer.parseInt(request.getParameter("page")));
				session.setAttribute("choixPage", true);
				
				numeroPage = (Integer.parseInt(request.getParameter("page"))-1)*nombreElement;
			}
			else if(request.getParameter("page") != null)
			{
				nombreElement = 20;
				int nombreComputer = computerServices.getCount(connection);
				int numberPage = (int) (Math.ceil((double)nombreComputer/nombreElement));
				session.setAttribute("numberPage", numberPage);
				request.setAttribute("currentPage", 1);
				session.setAttribute("choixPage", true);
			}
			else
			{
				session.setAttribute("choixPage", false);
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			nombreElement = 20;
			int nombreComputer = computerServices.getCount(connection);
			int numberPage = (int) (Math.ceil((double)nombreComputer/nombreElement));
			session.setAttribute("numberPage", numberPage);
			request.setAttribute("currentPage", 1);
			session.setAttribute("choixPage", true);
		}
		
		List<Computer> computers;
		
		computers = computerServices.get(numeroPage,nombreElement,connection);
		int nombre = computerServices.getCount(connection);
		
		
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionManager.closeConnection(connection);
		}
		
		ConnectionManager.closeConnection(connection);
		
		
		request.setAttribute("computers", computers);
		
		request.setAttribute("number_computer", nombre);
		
		this.getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
	}
}
