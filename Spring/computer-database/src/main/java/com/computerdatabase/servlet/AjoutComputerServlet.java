package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

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
//@WebServlet("/AjoutComputerServlet")
@Controller
public class AjoutComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Autowired
	ComputerServices computerServices;
	
	@Autowired
	CompanyServices companyServices;
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
	
	@RequestMapping(value = "/PageAjout", method = RequestMethod.GET)
	protected ModelAndView fonctionGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		List<Company> companys = new ArrayList<Company>();

		companys = companyServices.get();
		request.setAttribute("companys", companys);
		
		return new ModelAndView("addComputer");
/*
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(request, response);
*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	@RequestMapping(value="/AjoutComputer", method = RequestMethod.POST)
	protected ModelAndView fonctionPost(HttpServletRequest request,
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
		
		ComputerValidator.INSTANCE.test(computerDTO);
		Computer computer = Mapper.fromDTO(computerDTO);
		
		
		if(ComputerValidator.INSTANCE.getTableau().size() > 0)
		{
			List<Company> companys = new ArrayList<Company>();

			companys = companyServices.get();
			
			request.setAttribute("companys", companys);
			request.setAttribute("computer", computerDTO);
			request.setAttribute("error", ComputerValidator.INSTANCE);
			
			return new ModelAndView("addComputer");
			//this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
		}
		else
		{
			computerServices.put(computer);
			
			return new ModelAndView("redirect:affichage?page=1");
			//response.sendRedirect("affichage?page=1");
		}
	}
	
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	      config.getServletContext());
	  }

}
