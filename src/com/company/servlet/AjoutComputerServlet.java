package com.company.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.Company;
import com.company.bean.Computer;
import com.company.dao.CompanyDAO;
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
		
		List<Company> companys = new ArrayList<Company>();
				
		companys = CompanyDAO.getInstance().getListCompany();
		
		request.setAttribute("companys", companys);
		
		
		this.getServletContext().getRequestDispatcher("/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom ;
		if(request.getParameter("name").compareTo("") != 0 && request.getParameter("name") != null)
			nom = request.getParameter("name");
		else
			nom = null;
		
		
		String introduced_date = request.getParameter("introducedDate");
		String discontinued_date = request.getParameter("discontinuedDate");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date_introduced = null;
		Date date_discontinued = null;
		
		if(introduced_date.compareTo("") != 0 && introduced_date != null)
		{
			try {
				date_introduced = (Date) sdf.parse(introduced_date);
				System.out.println(date_introduced);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				date_introduced = (Date) sdf.parse("0000-00-00");
				System.out.println(date_introduced);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(discontinued_date.compareTo("") != 0 && discontinued_date != null)
		{
			try {
				date_discontinued = (Date) sdf.parse(discontinued_date);
				System.out.println(date_discontinued);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				date_discontinued = (Date) sdf.parse("0000-00-00");
				System.out.println(date_discontinued);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		int company;
		
		if(request.getParameter("company").compareTo("") != 0 && request.getParameter("company") != null)
			company = Integer.parseInt(request.getParameter("company"));
		else
			company = 0;
		
		/*
		 * Les données sont dans 
		 * nom
		 * date_introduced
		 * date_discontinued
		 * company
		 */
		
		
		ComputerDAO.getInstance().insererComputer(new Computer(0, nom, date_introduced, date_discontinued, new Company(company, null)));
		
		System.out.println(new Computer(0, nom, date_introduced, date_discontinued, new Company( company, null)));
		
		response.sendRedirect("affichage");
		//this.getServletContext().getRequestDispatcher("/affichage").forward(request, response);		
	}

}
