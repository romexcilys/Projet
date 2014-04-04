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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@Controller
@RequestMapping("/PageUpdate")
public class UpdateComputerServlet{

	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private CompanyServices companyServices;
	
	@Autowired  
    private ComputerValidator computerValidator;  
      
    @InitBinder  
    private void initBinder(WebDataBinder binder) {  
        binder.setValidator(computerValidator);  
    } 
    
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
	protected ModelAndView fonctionGet(@RequestParam(value="id", required=false) Integer id, @ModelAttribute("computerDTO") ComputerDTO computerDTO) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ModelAndView model = new ModelAndView("Formulaire");

		if (id != null) {
			
			computerDTO = computerServices.find(id);
			
			List<Company> companys = new ArrayList<Company>();
			companys = companyServices.get();
			model.addObject("companys", companys);
			model.addObject("computer", computerDTO);
			
			model.addObject("computerDTO", computerDTO);
			return model;
		}
		else
			return new ModelAndView("redirect:affichage?page=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView fonctionPost(@RequestParam(value="company", required= false) String company, @ModelAttribute("computerDTO") @Valid ComputerDTO computerDTO, BindingResult result) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ModelAndView model = new ModelAndView("Formulaire");
		
		computerDTO.setCompanyId(Integer.parseInt(company));
		
		if(result.hasErrors())
		{
			List<Company> companys = new ArrayList<Company>();

			companys = companyServices.get();
			model.addObject("companys", companys);
			model.addObject("computer", computerDTO);
			
			return model;
		}
		else
		{
			Computer computer = Mapper.fromDTO(computerDTO);
			computerServices.update(computer);
			return new ModelAndView("redirect:affichage?page=1");
		}
	}
	
}
