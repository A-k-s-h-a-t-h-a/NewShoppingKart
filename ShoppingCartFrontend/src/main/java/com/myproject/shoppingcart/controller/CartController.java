package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.domain.Cart;
import com.myproject.shoppingcart.domain.Product;

@Controller
public class CartController {

	Logger log= LoggerFactory.getLogger(CartController.class);
	
	@Autowired 
	HttpSession httpSession;

	@Autowired
	private CartDAO cartDAO; 
	
	@Autowired
	private Cart cart; 
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Product product;
		
	@GetMapping("cart/add/{productID}")					//Get or Post? //@PathVariable for both?
	public ModelAndView addToCart(@PathVariable("productID") String p_id)
	{
		log.debug("Starting of the addToCart method");
		
		ModelAndView mv= new ModelAndView("redirect:/");
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserId");
		if (loggedInUserID== null)
		{
			mv.addObject("errormsg", "Please login to add any product to cart");
			return mv;
		}
		product= productDAO.get(p_id);
		
		cart.setEmailid(loggedInUserID); 				//from Cart.java domain class
		cart.setProductName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setQuantity(1);
		cart.setProductID(p_id);
		cart.setId();
		
		if (cartDAO.save(cart)){
			mv.addObject("successmsg", "Product added to cart successfully");
		}
		else{
			mv.addObject("errormsg", "Could not add the product to cart. Please try again.");
		}

		log.debug("End of the addToCart method");
		return mv;
	}
	
	@GetMapping("/mycart")
	public ModelAndView getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("sinceUserClickedMyCart", true);
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		
		log.info("Logged in user id: "+ loggedInUserID);
		
		if (loggedInUserID== null)
		{
			mv.addObject("errormsg", "Please login to see your cart details");
			return mv;
		}
		
		else
		{
			List<Cart> carts= cartDAO.list(loggedInUserID);
			if (carts==null)
			{
				mv.addObject("noItems", "Your cart is empty");
			}
			else
			{
				mv.addObject("cartDetails", true);
				mv.addObject("cartList", carts);
				mv.addObject("size", carts.size());
				log.debug("No of products in cart"+ carts.size());
				log.debug("Ending of the method getMyCartDetails");
			}
			return mv;
		}
	}
	
	@GetMapping("/buy")
	public ModelAndView order()
	{
		ModelAndView mv= new ModelAndView("Home");
		boolean checkLoggedIn= (Boolean) httpSession.getAttribute("ifLoggedIn");
		if(checkLoggedIn== true)
		{
			String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
			mv.addObject("sinceUserClickedBuy", true);
		}
		else{
			mv.addObject("buyingError", "Please login to continue with the purchase");
		}
		return mv;
	}
}
