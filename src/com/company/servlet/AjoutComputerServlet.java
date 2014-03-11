package com.company.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.Computer;
import com.company.connexion.ConnexionSingleton;
import com.company.dao.ComputerDAO;

/**
 * Servlet implementation class AjoutComputerServlet
 */
@WebServlet("/AjoutComputerServlet")
public class AjoutComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom = request.getParameter("name");
		String introduced_date = request.getParameter("introducedDate");
		String discontinued_date = request.getParameter("discontinuedDate");
		
		String company_number = request.getParameter("company");
		int company = Integer.parseInt(company_number);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date_introduced = null;
		Date date_discontinued = null;
		
		try {
			date_introduced = (Date) sdf.parse(introduced_date);
			System.out.println(date_introduced);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			date_discontinued = (Date) sdf.parse(discontinued_date);
			System.out.println(date_discontinued);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Les données sont dans 
		 * nom
		 * date_introduced
		 * date_discontinued
		 * company
		 */
		
		
		ComputerDAO computerDao = new ComputerDAO();
		
		computerDao.insererComputer(new Computer(nom, date_introduced, date_discontinued, company, null));
		
		System.out.println(new Computer(nom, date_introduced, date_discontinued, company, "Coucou"));
		
		
		
		response.sendRedirect("affichage");
		//this.getServletContext().getRequestDispatcher("/affichage").forward(request, response);
		
	}

}
