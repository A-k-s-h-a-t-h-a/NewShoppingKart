
package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.Cart;
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
	
	@PostMapping("validate")
	public ModelAndView validate(@RequestParam("mailid") String mail, @RequestParam("psw") String password)
	{
		ModelAndView mv= new ModelAndView("Home"); 
		
		user= userDAO.validate(mail, password);
		if (user==null)
		{
			mv.addObject("fail", "Invalid email id or password");
		}
		else
		{
			httpSession.setAttribute("success", "Welcome "+ user.getFullname());
			httpSession.setAttribute("loggedInUserId", user.getEmailID());
			httpSession.setAttribute("ifLoggedIn", true);
			
			List<Cart> carts= cartDAO.list(user.getEmailID());
			httpSession.setAttribute("size", carts.size());
			httpSession.setAttribute("carts", carts);
			
			if (user.getRole()=='A')
			{
				httpSession.setAttribute("isAdmin", true);
			}
		}
		
		return mv;
	}
	
	@PostMapping("from_form")
	public ModelAndView from_form(@RequestParam("email") String mail, @RequestParam("psw") String password, 
			@RequestParam("mob") String mobile, @RequestParam("fullname") String name)
	{
		ModelAndView mv= new ModelAndView("Home");
		
		user.setEmailID(mail);
		user.setMobile(mobile);
		user.setFullname(name);
		user.setPwd(password);
		System.out.println(user.getEmailID());
		userDAO.save(user);
		
		httpSession.setAttribute("success", "Welcome "+ user.getFullname()+ " You have successfully created an account with us ");
		
		return mv;
	}
}
