package com.computerdatabase.servlet;

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

import com.computerdatabase.dao.CompanyDAO;
import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;

/**
 * Servlet implementation class UpdateComputerServlet
 */
@WebServlet("/UpdateComputerServlet")
public class UpdateComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idComputer = request.getParameter("id");
		
		if(idComputer != null)
		{
			int id = Integer.parseInt(idComputer.trim());
			Computer computer = ComputerDAO.getInstance().findComputer(id);
			List<Company> companys = new ArrayList<Company>();
			
			companys = CompanyDAO.getInstance().getListCompany();
			
			request.setAttribute("companys", companys);
			
			request.setAttribute("computer", computer);
			request.setAttribute("companyId", computer.getCompany().getId());
			
		}
		
		this.getServletContext().getRequestDispatcher("/Formulaire.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idString = request.getParameter("idComputer");
		int idComputer = 0;
		
		if(idString != null)
			idComputer = Integer.parseInt(idString);
		
		String nom ;
		if(request.getParameter("name").compareTo("") != 0 && request.getParameter("name") != null)
			nom = request.getParameter("name");
		else
			nom = null;
		
		String introducedDate = request.getParameter("introducedDate");
		String discontinuedDate = request.getParameter("discontinuedDate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateIntroduced = null;
		Date dateDiscontinued = null;
		
		if(introducedDate.compareTo("") != 0 && introducedDate != null)
		{
			try {
				dateIntroduced = (Date) sdf.parse(introducedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				dateIntroduced = (Date) sdf.parse("0000-00-00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(discontinuedDate.compareTo("") != 0 && discontinuedDate != null)
		{
			try {
				dateDiscontinued = (Date) sdf.parse(discontinuedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				dateDiscontinued = (Date) sdf.parse("0000-00-00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		int company = 0;
		
		if(request.getParameter("company").compareTo("") != 0 && request.getParameter("company") != null)
			company = Integer.parseInt(request.getParameter("company"));
		
		/*
		 * Les données sont dans 
		 * nom
		 * date_introduced
		 * date_discontinued
		 * company
		 */
		
		ComputerDAO.getInstance().editComputer(Computer.builder().id(idComputer).name(nom).introduced(dateIntroduced).discontinued(dateDiscontinued).company(Company.builder().id(company).build()).build());
		//ComputerDAO.getInstance().insererComputer(new Computer(0, nom, dateIntroduced, dateDiscontinued, new Company(company, null)));
		
		response.sendRedirect("affichage");
		
	}
}
