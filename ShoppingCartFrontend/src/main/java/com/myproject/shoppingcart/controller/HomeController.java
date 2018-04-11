package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	Logger log= LoggerFactory.getLogger(HomeController.class);
	
//	private static String imageDirectory= "D:\\aks\\NewShoppingKart\\ShoppingCartFrontend\\src\\main\\webapp\\resources\\images\\ShoppingCartImages";
	
	@GetMapping("/")
	public ModelAndView h(/*HttpServletRequest request*/)
	{
		log.debug("Start of the home method");
		
		ModelAndView mv= new ModelAndView("Home");
		List<Category> categories= categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		mv.addObject("carouselDisplayedOnce", true);
		
//		httpSession.setAttribute("imageDirectory", imageDirectory);
//		String root= request.getContextPath();
//		String imageFolder =  root + File.separator +"src" + File.separator + "main" +File.separator + "webapp"+File.separator + "resources"+File.separator;	
//		String p= request.getContextPath();
//	    String path= httpSession.getServletContext().getContextPath();
		
		log.debug("End of the home method");
		return mv;										
	}
	
	@GetMapping("/home")
	public ModelAndView home()
	{
		log.debug("Start of the home method for one instance");
		
		ModelAndView mv= new ModelAndView("Home");
		
		List<Category> categories= categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		mv.addObject("brandLogoClicked", true);
		mv.addObject("carouselDisplayedOnce", true);
		
		log.debug("End of the home method for one instance");
		return mv;
	}
	
	
	@GetMapping("/signin")
	public ModelAndView l()
	{
		log.debug("Start of the sign in method");
		
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("sinceUserClickedSignIn", true);
		
		log.debug("End of the sign in method");
		return mv;
	}
	
	@GetMapping("/signup")
	public ModelAndView r()
	{
		log.debug("Start of the sign up method");
		
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("sinceUserClickedSignUp", true);
		
		log.debug("End of the sign up method");
		return mv;
	}
	
	@GetMapping("/signout")
	public ModelAndView lg()
	{
		//at the time of login, we add userid in http session
		//at the time of logout, we need to remove user id from http session
		log.debug("Start of the logout method");
		
		ModelAndView mv= new ModelAndView("Home");
		httpSession.removeAttribute("loggedInUserId");
		httpSession.removeAttribute("ifLoggedIn");
		httpSession.removeAttribute("isAdmin");
		httpSession.removeAttribute("success");
		mv.addObject("carouselDisplayedOnce", true);
		mv.addObject("logoutmessage", "You have successfully logged out");
		
		log.debug("End of the logout method");
		return mv;
	}

}
