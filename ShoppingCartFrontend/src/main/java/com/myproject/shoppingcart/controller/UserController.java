package com.myproject.shoppingcart.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.Cart;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.User;

@Controller
public class UserController {

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
	
//	@PostMapping("validate")
//	public ModelAndView validate(@RequestParam("mailid") String mail, @RequestParam("psw") String password)
//	{
//		log.debug("Starting of the validate method");
//		
//		ModelAndView mv= new ModelAndView("Home"); 
//		user= userDAO.validate(mail, password);
//		if (user==null)
//		{
//			mv.addObject("fail", "Invalid email id or password");
//		}
//		else
//		{
//			httpSession.setAttribute("success", "Welcome "+ user.getFullname());
//			httpSession.setAttribute("loggedInUserId", user.getEmailID());
//			httpSession.setAttribute("ifLoggedIn", true);
//			
//			mv.addObject("carouselDisplayedOnce", true);
//
//			List<Cart> carts= cartDAO.list(user.getEmailID());
//			httpSession.setAttribute("size", carts.size());
//			httpSession.setAttribute("carts", carts);
//			
//			if (user.getRole()=='A'){
//				httpSession.setAttribute("isAdmin", true);
//			}
//		}
//
//		log.debug("End of the validate method");
//		return mv;
//	}
	
	@PostMapping("from_form")
	public ModelAndView from_form(@RequestParam("email") String mail, @RequestParam("psw") String password, 
								  @RequestParam("mob") String mobile, @RequestParam("fullname") String name, 
								  @RequestParam("state") String state)
	{
		log.debug("Starting of the from_form method");
		
		ModelAndView mv= new ModelAndView("Home");
		
		user.setEmailID(mail);
		user.setMobile(mobile);
		user.setFullname(name);
		user.setPwd(password);
		user.setState(state);

		userDAO.save(user);
		
		mv.addObject("success", "Welcome "+ user.getFullname()+ ". You have successfully created an account with us ");
		mv.addObject("sinceUserClickedLogin", true);

		log.debug("End of the from_form method");
		return mv;
	}
	
	@RequestMapping(value="/secure")
	public ModelAndView secure(Principal p)
	{
		ModelAndView mv= new ModelAndView("Home");
		user=userDAO.getbyname(p.getName());
		httpSession.setAttribute("success", "Welcome "+ user.getFullname());
		System.out.println("mail id of user "+user.getFullname());
		httpSession.setAttribute("loggedInUserId", user.getEmailID());
		httpSession.setAttribute("ifLoggedIn", true);					//used in login-header to (not) display sign in/out 
		
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
	
	@RequestMapping(value="/secured")
	public ModelAndView securedAdmin()
	{
		ModelAndView mv= new ModelAndView();
		mv.addObject("isAdmin", true);
		return mv;
	}
}
