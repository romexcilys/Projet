package com.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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
 * Servlet implementation class AjoutComputerServlet
 */
//@WebServlet("/AjoutComputerServlet")
@Controller
public class AjoutComputerServlet{

	
	@Autowired
	private Validator validator;
	
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
		ComputerDTO computerDTO = new ComputerDTO();
		request.setAttribute("computerDTO", computerDTO);
		companys = companyServices.get();
		request.setAttribute("companys", companys);
		System.out.println(companys);
		return new ModelAndView("addComputer");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	@RequestMapping(value="/AjoutComputer", method = RequestMethod.POST)
	protected ModelAndView fonctionPost(Model model,HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("computerDTO") ComputerDTO computerDTO, BindingResult result) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		ComputerValidator computerValidator = new ComputerValidator();
		
		computerValidator.validate(computerDTO, result);
		
		if(result.hasErrors())
		{
			List<Company> companys = new ArrayList<Company>();
			companys = companyServices.get();
			//request.setAttribute("companys", companys);
			request.setAttribute("computerDTO", computerDTO);
			System.out.println(request.getAttribute("companys"));
			request.setAttribute("computer", computerDTO);
			
			System.out.println(companys);
			
			return new ModelAndView("addComputer");
		}
		else
		{
			System.out.println("1");
			Computer computer = Mapper.fromDTO(computerDTO);
			System.out.println("2");
			computerServices.put(computer);
			System.out.println("3");
			return new ModelAndView("redirect:affichage?page=1");
		}
		
	}
	
}
