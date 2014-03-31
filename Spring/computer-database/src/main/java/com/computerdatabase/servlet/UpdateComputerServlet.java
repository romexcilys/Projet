package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.computerdatabase.domain.Company;
import com.computerdatabase.domain.Computer;
import com.computerdatabase.dto.ComputerDTO;
import com.computerdatabase.mapper.Mapper;
import com.computerdatabase.service.CompanyServices;
import com.computerdatabase.service.ComputerServices;
import com.computerdatabase.validator.ComputerValidator;

/**
 * Servlet implementation class UpdateComputerServlet
 */
@WebServlet("/UpdateComputerServlet")
public class UpdateComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	ComputerServices computerServices;
	
	@Autowired
	CompanyServices companyServices;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateComputerServlet() {
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
		

		String idComputer = request.getParameter("id");

		if (idComputer != null) {
			int id = Integer.parseInt(idComputer.trim());
			ComputerDTO computerDTO = computerServices.find(id);
			List<Company> companys = new ArrayList<Company>();

			companys = companyServices.get();

			
			request.setAttribute("companys", companys);
			request.setAttribute("computer", computerDTO);

		}

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/Formulaire.jsp")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String idString = null;
		if (request.getParameter("idComputer") != null)
			idString = request.getParameter("idComputer");

		int idComputer = 0;
		if (idString != null)
			idComputer = Integer.parseInt(idString);

		String nom = null;
		if (request.getParameter("name") != null && request.getParameter("name").compareTo("") != 0
			)
			nom = request.getParameter("name");

		String introducedDate = null;
		if (request.getParameter("introducedDate") != null
				&& request.getParameter("introducedDate").compareTo("") != 0)
			introducedDate = request.getParameter("introducedDate");

		String discontinuedDate = null;
		if (request.getParameter("discontinuedDate") != null
				&& request.getParameter("discontinuedDate").compareTo("") != 0)
			discontinuedDate = request.getParameter("discontinuedDate");

		int idCompany = 0;
		if (request.getParameter("company") != null
				&& request.getParameter("company").compareTo("") != 0)
			idCompany = Integer.parseInt(request.getParameter("company"));
		
		ComputerDTO computerDTO = ComputerDTO.Builder().id(idComputer).nom(nom)
				.introducedDate(introducedDate)
				.discontinuedDate(discontinuedDate).companyId(idCompany)
				.build();

		ComputerValidator.INSTANCE.test(computerDTO);
		
		if(ComputerValidator.INSTANCE.getTableau().size() > 0)
		{
			List<Company> companys = new ArrayList<Company>();

			companys = companyServices.get();
			request.setAttribute("companys", companys);
			
			request.setAttribute("computer", computerDTO);
			request.setAttribute("error", ComputerValidator.INSTANCE);
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Formulaire.jsp").forward(request, response);
		}
		else
		{
			Computer computer = Mapper.fromDTO(computerDTO);
			
			computerServices.update(computer);
			
			response.sendRedirect("affichage?page=1");
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	      config.getServletContext());
	  }
}
