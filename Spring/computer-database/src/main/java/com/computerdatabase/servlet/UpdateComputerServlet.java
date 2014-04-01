package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
//@WebServlet("/UpdateComputerServlet")
@Controller
@RequestMapping("/PageUpdate")
public class UpdateComputerServlet{

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
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView fonctionGet(HttpServletRequest request,
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
		
		return new ModelAndView("Formulaire");
		/*this.getServletContext()
				.getRequestDispatcher("/WEB-INF/Formulaire.jsp")
				.forward(request, response);*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView fonctionPost(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ComputerValidator computerValidator = new ComputerValidator();
		
		computerValidator.validate(computerDTO, result);
		
		if(result.hasErrors())
		{
			List<Company> companys = new ArrayList<Company>();

			companys = companyServices.get();
			request.setAttribute("companys", companys);
			
			request.setAttribute("computer", computerDTO);
			
			return new ModelAndView("Formulaire");
		}
		else
		{
Computer computer = Mapper.fromDTO(computerDTO);
			
			computerServices.update(computer);
			
			return new ModelAndView("redirect:affichage?page=1");
		}

	
	}
	
}
