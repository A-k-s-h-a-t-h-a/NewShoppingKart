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
import com.myproject.shoppingcart.domain.User;

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
		
	@Autowired
	private User user; 
	
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
		
		cart.setEmailID(loggedInUserID); 				//from Cart.java domain class
		cart.setProductName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setQuantity(1);
		cart.setProductID(p_id);
		
		List<Cart> usercart= cartDAO.list(user.getEmailID());
		if (usercart== null)
		{
			cartDAO.save(cart);
		}
		else{
			cartDAO.update(cart);
		}
		
			mv.addObject("successmsg", "Product added to cart successfully");		
			httpSession.setAttribute("size", usercart.size());
			httpSession.setAttribute("carts", usercart);

		
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
					System.out.println(row.getProductName()+" "+row.getEmailID());
				}
				mv.addObject("size", usercart.size());
				log.debug("No of products in cart"+ usercart.size());
				log.debug("Ending of the method getMyCartDetails");
			}
			return mv;
		}
	}
	
	@GetMapping("/increase/{id}")
	public ModelAndView increaseProductQuantity(@PathVariable("id") int id)
	{
		log.debug("Starting of the method increaseProductQuantity");
		ModelAndView mv= new ModelAndView("redirect:/mycart");
		cart=cartDAO.get(id);
		cart.setQuantity((cart.getQuantity()+1));
		cartDAO.save(cart);
		
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		List<Cart> usercart= cartDAO.list(loggedInUserID);
		
		log.debug("Ending of the method increaseProductQuantity");
		return mv;
	}

	@GetMapping("/decrease/{id}")
	public ModelAndView decreaseProductQuantity(@PathVariable("id") int id)
	{
		log.debug("Starting of the method decreaseProductQuantity");
		ModelAndView mv= new ModelAndView("redirect:/mycart");
		
		cart=cartDAO.get(id);
		cart.setQuantity((cart.getQuantity()-1));
		cartDAO.save(cart);
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		List<Cart> usercart= cartDAO.list(loggedInUserID);
		
		log.debug("Ending of the method decreaseProductQuantity");
		return mv;
	}
	
	@GetMapping("/remove/{id}")
	public ModelAndView removeProductFromCart(@PathVariable("id") int id)
	{
		log.debug("Starting of the method removeProductFromCart");
		ModelAndView mv= new ModelAndView("redirect:/mycart");		
		if (cartDAO.delete(id)==true){
			mv.addObject("cartProductSucccess", "Product deleted from cart");
			String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
			List<Cart> usercart= cartDAO.list(loggedInUserID);
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
		log.debug("Starting of the method order");
		ModelAndView mv= new ModelAndView("Home");
		
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		List<Cart> usercart= cartDAO.list(loggedInUserID);
		System.out.println(loggedInUserID + " buying" + usercart);
		if(loggedInUserID!= null)
		{	
			if(usercart!= null)
			{
				mv.addObject("sinceUserClickedBuy", true);
			}
		}
		else{
			mv.addObject("buyingError", "Please login to continue with the purchase");
		}
		log.debug("Ending of the method order");
		
		return mv;
	}
}
