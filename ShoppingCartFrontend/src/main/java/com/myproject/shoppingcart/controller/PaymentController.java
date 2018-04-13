package com.myproject.shoppingcart.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.Payment;
import com.myproject.shoppingcart.domain.User;

@Controller
public class PaymentController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;
	
	@Autowired 
	HttpSession httpSession;
	
	Logger log= LoggerFactory.getLogger(PaymentController.class);
	
	
	@ModelAttribute("shipping")
	public User f1()
	{
		return new User();
	}
	
	@PostMapping("proceed")
	public ModelAndView proceedToPayment(@ModelAttribute("shipping")  User shipping)
	{
		log.debug("Starting of the proceed to payment method");
		
		ModelAndView mv= new ModelAndView("Home"); 
		
		if (userDAO.save(shipping)== true)
		{
			mv.addObject("proceedingToPayment", true);
		}
		else{
			mv.addObject("cannotProceed", "Unable to proceed to payment. Please check your details again");
		}
		log.debug("End of the proceed to payment method");
		return mv;
	}
	
//	@PostMapping("pay")
//	public ModelAndView payAmount(@ModelAttribute("payment") Payment purchase)
//	{
//		log.debug("Starting of the payment method");
//		
//		ModelAndView mv= new ModelAndView("Home"); 
//		//User purchase= new User();
//		if (paymentDAO.save(purchase)== true)
//		{
//			mv.addObject("finalPaymentDone", "Your payment was confirmed.");
//		}
//		else{
//			mv.addObject("finalPaymentFailed", "Payment was unsuccessful.");
//		}
//		log.debug("End of the payment method");
//		return mv;
//	}
}
