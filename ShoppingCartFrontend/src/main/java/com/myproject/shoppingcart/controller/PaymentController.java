package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.dao.PaymentDAO;
import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.Cart;
import com.myproject.shoppingcart.domain.Payment;
import com.myproject.shoppingcart.domain.Product;
import com.myproject.shoppingcart.domain.User;

@Controller
public class PaymentController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;
	
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
	HttpSession httpSession;
	
	Logger log= LoggerFactory.getLogger(PaymentController.class);
	
	
	@ModelAttribute("payment")
	public Payment f1()
	{
		return new Payment();
	}
	@PostMapping("/proceed")
	public ModelAndView proceedToPayment( @ModelAttribute("payment")  Payment payment, Model model)
	{
		log.debug("Starting of the proceed to payment method");
		
		ModelAndView mv= new ModelAndView("Home"); 
				
		if (paymentDAO.save(payment)== true){
			mv.addObject("proceedingToPayment", true);
			
			String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
			List<Cart> usercart= cartDAO.list(loggedInUserID);
			
			int subtotal, grandTotal=0;
			for(Cart row:usercart)
			{
				subtotal= row.getQuantity()* row.getPrice();
				grandTotal= grandTotal + subtotal;
				model.addAttribute("grandTotal", grandTotal);
			}
		}
		else{
			mv.addObject("cannotProceed", "Unable to proceed to payment. Please check your details again");
		}
		log.debug("End of the proceed to payment method");
		return mv;
	}
	
	
	@PostMapping("/pay")
	public ModelAndView payAmount(Payment purchase)
	{
		log.debug("Starting of the payment method");
		
		ModelAndView mv= new ModelAndView("Home"); 
		if (paymentDAO.save(purchase)== true)
		{
			mv.addObject("finalPaymentDone", "Your payment was confirmed.");
			mv.addObject("orderPlaced", true);
			
//			List<Product> products= productDAO.list();
//			for(Product row:products)
//			{
//				product=productDAO.get(cart.getId());
//				product.setStock((product.getStock()-1));
//				productDAO.save(product);
//				if (product.getStock()==0)
//				{
//							mv.addObject("over", "This product is out of stock");
//				}
//			}
			
		}
		else{
			mv.addObject("finalPaymentFailed", "Payment was unsuccessful.");
		}
		log.debug("End of the payment method");
		return mv;
	}
	
	
	@PostMapping("/check")
	public ModelAndView checkStock(String id)
	{
		log.debug("Starting of the checkStock method");
		
		ModelAndView mv= new ModelAndView("Home");
		List<Product> products= productDAO.list();
		for(Product row:products)
		{
			product=productDAO.get(id);
			product.setStock((product.getStock()-1));
			productDAO.save(product);
			if (product.getStock()==0)
			{
						mv.addObject("over", "This product is out of stock");
			}
		}
		log.debug("End of the checkStock method");
		return mv;
	}
	
}
