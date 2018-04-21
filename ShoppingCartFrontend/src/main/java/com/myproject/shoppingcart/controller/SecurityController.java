package com.myproject.shoppingcart.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.Cart;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.User;

@Controller
public class SecurityController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	CategoryDAO categoryDAO;
	
	Logger log= LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/secure")
	public ModelAndView secure(Principal p)
	{
		ModelAndView mv= new ModelAndView("Home");
		user=userDAO.getbyname(p.getName());
		httpSession.setAttribute("success", "Welcome "+ user.getFullname());
		System.out.println("mail id of user "+user.getFullname());
		httpSession.setAttribute("loggedInUserId", user.getEmailID());
		httpSession.setAttribute("ifLoggedIn", true);
		
		mv.addObject("carouselDisplayedOnce", true);
		
		System.out.println("logging in" + user.getFullname());
		if (user.getRole().equals("ROLE_ADMIN"))
		{
			System.out.println("logging in" + user.getFullname());
			httpSession.setAttribute("isAdmin", true);
			return mv;
		}
		List<Cart> carts= cartDAO.list(user.getEmailID());
		
		List<Category> categories= categoryDAO.list();
		httpSession.setAttribute("categoryList", categories);
		httpSession.setAttribute("size", carts.size());	
		httpSession.setAttribute("carts", carts);
		
		return mv;
	}
	
	@RequestMapping(value="/loginError")
	public ModelAndView loginError()
	{
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("fail", "Invalid email id or password");
		return mv;
	}
	
	@RequestMapping(value="/accessDenied")
	public ModelAndView wrongUser()
	{
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("forbidden", "Access Denied");
		return mv;
	}
}
