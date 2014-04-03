package com.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.computerdatabase.service.ComputerServices;

/**
 * Servlet implementation class UpdateComputerServlet
 */
@Controller
@RequestMapping("/PageDelete")
public class DeleteComputerServlet {

	@Autowired
	private ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView fonctionGet(
			@RequestParam(value = "id", required = false) Integer id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView("redirect:affichage?page=1");

		if(id == null)
			id= -1;

		computerServices.delete(id);

		return model;
	}

}
