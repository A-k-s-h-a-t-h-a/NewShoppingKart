package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.domain.Cart;

@Controller
public class CartController {

	Logger log= LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartDAO cartDAO; 
	
	@Autowired
	private Cart cart; 
	
	@Autowired 
	HttpSession httpSession;
	
	@PostMapping("product/cart/add")
	public ModelAndView addToCart(@RequestParam String productName, @RequestParam String quantity, @RequestParam int price)
	{
		ModelAndView mv= new ModelAndView("Home");
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserID");
		if (loggedInUserID== null)
		{
			mv.addObject("errormsg", "Please login to add any product to cart");
			return mv;
		}
		
//		cart.setEmailID(httpSession.getAttribute("loggedInUserID"));
		cart.setEmailid(loggedInUserID);				//from Cart.java domain class
		cart.setPrice(price);
		cart.setQuantity(Integer.parseInt(quantity));
		
		if (cartDAO.save(cart)){
			mv.addObject("successmsg", "Product added to cart successfully");
		}
		else
		{
			mv.addObject("errormsg", "Could not add the product to cart. Please try again.");
		}
		return mv;
	}
	
	@GetMapping("/mycart")
	public ModelAndView getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv= new ModelAndView("Home");
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id: "+ loggedInUserID);
		
		if (loggedInUserID== null)
		{
			mv.addObject("errormsg", "Please login to see your cart details");
			return mv;
		}
		List<Cart> cartList= cartDAO.list(loggedInUserID);
		mv.addObject("cartList", cartList);
//		mv.addObject("isUserClickedMyCart", true);
		
		log.debug("No of products in cart"+ cartList.size());
		log.debug("Ending of the method getMyCartDetails");
		return mv;
	}
}
