package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.domain.Category;

@Controller
public class HomeController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;

	@Autowired
	HttpSession httpSession;
	
	@GetMapping("/")										//http://localhost:8080/ShoppingCartFrontend
	public ModelAndView h()
	{
		ModelAndView mv= new ModelAndView("Home");
		List<Category> categories= categoryDAO.list();		//		mv.addObject("username", "Rupsa");
		//mv.addObject("categories", categories); 			//		mv.addObject("error","no error");
		httpSession.setAttribute("categories", categories);
		return mv;										
	}
	
	@GetMapping("/login") //mapping
	public ModelAndView l()
	{
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("sinceUserClickedLogin", true);
		return mv;
	}
	
	@GetMapping("/register")
	public ModelAndView r()
	{
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("sinceUserClickedRegister", true);
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView lg()
	{
		//at the time of login, we add userid in http session
		//at the time of logout, we need to remove user id from http session
		ModelAndView mv= new ModelAndView("Home");
		
		//httpSession.invalidate();
		httpSession.removeAttribute("loggedInUserId");
		httpSession.removeAttribute("ifLoggedIn");
		httpSession.removeAttribute("isAdmin");
		httpSession.removeAttribute("success");
		mv.addObject("logoutmessage", "You have successfully logged out");
		
		return mv;
	}

	@GetMapping("/home")
	public ModelAndView hm()
	{
		ModelAndView mv= new ModelAndView("Home");
		//mv.addObject("isAdmin",false);
		return mv;
	}
}
