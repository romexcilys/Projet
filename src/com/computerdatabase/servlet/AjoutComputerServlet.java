package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dto.ComputerDTO;
import com.computerdatabase.mapper.Mapper;
import com.computerdatabase.service.CompanyServices;
import com.computerdatabase.service.ComputerServices;
import com.computerdatabase.validator.ComputerValidator;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Company> companys = new ArrayList<Company>();

		companys = CompanyServices
				.getInstance().get();
		request.setAttribute("companys", companys);

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nom = null;
		if (request.getParameter("name") != null && request.getParameter("name").compareTo("") != 0
			)
			nom = request.getParameter("name");

		String introducedDate = null;
		if(request.getParameter("introducedDate") != null && request.getParameter("introducedDate").compareTo("") != 0)
			introducedDate = request.getParameter("introducedDate");
		
		String discontinuedDate = null;
		if(request.getParameter("discontinuedDate") != null && request.getParameter("discontinuedDate").compareTo("") != 0)
			discontinuedDate = request.getParameter("discontinuedDate");

		int idCompany = 0;

		if (request.getParameter("company") != null && request.getParameter("company").compareTo("") != 0
				)
			idCompany = Integer.parseInt(request.getParameter("company"));
		
		
		System.out.println("Id computer : "+idCompany);
		
		ComputerDTO computerDTO = ComputerDTO.Builder().id(0).nom(nom).introducedDate(introducedDate).discontinuedDate(discontinuedDate).companyId(idCompany).build();
		
		ComputerValidator.getInstance().test(computerDTO);
		Computer computer = Mapper.fromDTO(computerDTO);
		
		
		if(ComputerValidator.getInstance().getTableau().size() > 0)
		{
			List<Company> companys = new ArrayList<Company>();

			companys = CompanyServices
					.getInstance().get();
			
			request.setAttribute("companys", companys);
			request.setAttribute("computer", computerDTO);
			request.setAttribute("error", ComputerValidator.getInstance());
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
		}
		else
		{
			ComputerServices
			.getInstance().put(computer);
			response.sendRedirect("affichage?page=1");
		}
	}

}
