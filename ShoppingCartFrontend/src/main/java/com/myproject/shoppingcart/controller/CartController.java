package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.dao.PaymentDAO;
import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.domain.Cart;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.Payment;
import com.myproject.shoppingcart.domain.Product;
import com.myproject.shoppingcart.domain.User;

@Controller
public class CartController {

	Logger log= LoggerFactory.getLogger(CartController.class);
	
	@Autowired 
	HttpSession httpSession;

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private CartDAO cartDAO; 
	
	@Autowired
	private Cart cart; 

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Product product;

	@Autowired
	private PaymentDAO paymentDAO;
	
	@Autowired
	private Payment payment; 
		
	@Autowired
	private User user; 
	
	@GetMapping("/cart/add/{productID}")					//Get or Post?
	public ModelAndView addToCart(@PathVariable("productID") String p_id)
	{	System.out.println("Trying to add to cart");
		
		log.debug("Starting of the addToCart method");
		
		ModelAndView mv= new ModelAndView("redirect:/");
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserId");
		
//		System.out.println("Loggedin mail id in cart ctrler "+loggedInUserID);
//		System.out.println(loggedInUserID);
//		if (loggedInUserID== null)
//		{
//			System.out.println(loggedInUserID);
//			System.out.println("Please login to add any product to cart");
//			httpSession.setAttribute("errormsg", "Please login to add any product to cart");
//			return mv;
//		}
		product= productDAO.get(p_id);
		
		cart.setEmailID(loggedInUserID); 				//from Cart.java domain class
		cart.setProductName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setQuantity(1);
		cart.setProductID(p_id);
		
		List<Cart> usercart= cartDAO.list(loggedInUserID);
		if(usercart.size()==0)
		{
			if(product.getStock()>0){
				cartDAO.save(cart);
				System.out.println("cart is added when cart size is "+usercart.size());
			}
		}
		else
		{
			for(Cart row:usercart)
			{
				if (row.getProductID().equals(p_id)/* && row.getEmailID().equals(loggedInUserID)*/)
				{
					row.setQuantity(row.getQuantity()+1);
					if(product.getStock()>0){
						cartDAO.update(row);
						System.out.println("cart is updated when cart size is "+usercart.size());
					}
	
					mv.addObject("successmsg", "Product added to cart successfully");		
					httpSession.setAttribute("size", usercart.size());
					httpSession.setAttribute("carts", usercart);
					return mv;
				}
			}
			if(product.getStock()>0){
				cartDAO.save(cart);
				System.out.println("cart is added in loop when cart size is "+usercart.size());
			}
			
			mv.addObject("successmsg", "Product added to cart successfully");		
			httpSession.setAttribute("size", usercart.size());
			httpSession.setAttribute("carts", usercart);
		
		}
		
		log.debug("End of the addToCart method");
		return mv;
	}
	
	@GetMapping("/mycart")
	public ModelAndView getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		
		ModelAndView mv= new ModelAndView("Home");
		List<Category> categories= categoryDAO.list();
		httpSession.setAttribute("categoryList", categories);
		
		mv.addObject("sinceUserClickedMyCart", true);
		httpSession.setAttribute("buyAllInTheCart", true);
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");

		log.info("Logged in user id: "+ loggedInUserID);
		
		if (loggedInUserID== null)
		{
			mv.addObject("errormsg", "Please login to see your cart details");
		}
		else
		{
			List<Cart> usercart= cartDAO.list(loggedInUserID);
			System.out.println(usercart+" is empty");
			if (usercart==null)
			{
				mv.addObject("emptyCart", true);
				mv.addObject("noItems", "Your cart is empty");
				System.out.println("cart inside if condition is empty");
			}
			else
			{
				httpSession.setAttribute("cartList", usercart);
				mv.addObject("size", usercart.size());
				
				log.debug("No of products in cart"+ usercart.size());
			}
		}
		log.debug("Ending of the method getMyCartDetails");
		return mv;
	}
	
	@GetMapping("/increase/{id}")
	public ModelAndView increaseProductQuantity(@PathVariable("id") int id)
	{
		log.debug("Starting of the method increaseProductQuantity");
		ModelAndView mv= new ModelAndView("redirect:/mycart");
		
		cart=cartDAO.get(id);
		product=productDAO.get((cart.getProductID()));
		if(product.getStock()>cart.getQuantity())
		{
			cart.setQuantity((cart.getQuantity()+1));
			cartDAO.update(cart);
		}
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
		if(cart.getQuantity()>1)
		{
			cart.setQuantity((cart.getQuantity()-1));
			cartDAO.update(cart);
		}
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
		
		if (cartDAO.delete(id)==true)
		{
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
	
	@GetMapping("/empty")
	public ModelAndView emptyCart()
	{
		log.debug("Starting of the method emptyCart");
		ModelAndView mv= new ModelAndView("redirect:/");
		
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserId");
		List<Cart> usercart= cartDAO.list(loggedInUserID);
		for(Cart row:usercart)
		{
			cartDAO.delete(row.getId());
		}
		httpSession.setAttribute("cartList", usercart);
		
		log.debug("Ending of the method emptyCart");
		return mv;
	}
	
	@GetMapping("/buy")
	public ModelAndView order(@RequestParam("buyreq") String buyreq, @RequestParam(value="prid",required = false) String pr_id)
	{
		log.debug("Starting of the method order");
		ModelAndView mv= new ModelAndView("Home");
		
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		if(loggedInUserID!= null)
		{	
			List<Cart> usercart= cartDAO.list(loggedInUserID);
			if(buyreq== "cart" && usercart!= null)
			{
				int subtotal=0, grandTotal=0, qty=0, totalQty=0;
				String all_pr_names = null, one_pr_name = null;
				
				for(Cart row:usercart)
					{
						subtotal= row.getQuantity()* row.getPrice();
						grandTotal= grandTotal + subtotal;
						payment.setSubTotal(subtotal);
						payment.setGrandTotal(grandTotal);
						
						all_pr_names=all_pr_names + row.getProductName();
						payment.setProductName(all_pr_names);
						
						qty= row.getQuantity();
						totalQty= totalQty + qty;
						System.out.println(totalQty);
						
					}
				payment.setQuantity(totalQty);
			}
			else if(buyreq== "prpage")
			{
				System.out.println(buyreq);
				product= productDAO.get(pr_id);
				payment.setSubTotal(product.getPrice());
				payment.setProductName(product.getName()); 
			}
			httpSession.setAttribute("purchaseDetails", payment);
			mv.addObject("sinceUserClickedBuy", true);
		}
		
//		else{
//			mv.addObject("buyingError", "Please login to continue with the purchase");
//		}
		log.debug("Ending of the method order");
		return mv;
	}
	
	@ModelAttribute("payment")
	public Payment f1()
	{
		return new Payment();
	}
}
