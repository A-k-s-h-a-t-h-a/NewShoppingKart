package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
			List<Cart> usercart= cartDAO.list(loggedInUserID);
			if (usercart==null)
			{
				mv.addObject("noItems", "Your cart is empty");
				System.out.println("cart is empty");
			}
			else
			{
				httpSession.setAttribute("cartList", usercart);
				for(Cart row:usercart)
				{
					System.out.println(row.getProductName()+" "+row.getEmailid());
				}
				mv.addObject("size", usercart.size());
				log.debug("No of products in cart"+ usercart.size());
				log.debug("Ending of the method getMyCartDetails");
			}
			return mv;
		}
	}
	
	@GetMapping("/remove")
	public ModelAndView removeProductFromCart(@RequestParam("id") int id)
	{
		log.debug("Starting of the method removeProductFromCart");
		ModelAndView mv= new ModelAndView("redirect:/");		
		if (cartDAO.delete(id)==true){
			mv.addObject("cartProductSucccess", "Product deleted from cart");
			
		}
		else{
			mv.addObject("cartProductFail", "Product could not be deleted from cart");
		}
		log.debug("Ending of the method removeProductFromCart");
		return mv;
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
