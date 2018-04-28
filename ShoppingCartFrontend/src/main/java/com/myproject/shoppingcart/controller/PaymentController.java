package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView proceedToPayment( @ModelAttribute("payment")  Payment order, Model model)
	{
		log.debug("Starting of the proceed to payment method");
		ModelAndView mv= new ModelAndView("Home"); 
		
		String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
		List<Payment> userorder= paymentDAO.list(loggedInUserID);
		String purchase= (String)httpSession.getAttribute("purchaseDetails");
		
		for(Payment row:userorder)
		{
			if (row.getId()== order.getId())
			{
				int grandTotal= order.getSubTotal()* order.getQuantity();
				order.setGrandTotal(grandTotal);
				httpSession.setAttribute("purchase", order);
				
				mv.addObject("proceedingToPayment", true);
			}
			else{
				mv.addObject("cannotProceed", "Unable to proceed to payment. Please check your details again");
			}
		}
		log.debug("End of the proceed to payment method");
		return mv;
	}
	
	
	@PostMapping("/pay")
	public ModelAndView payAmount()
	{
		log.debug("Starting of the payment method");
		ModelAndView mv= new ModelAndView("Home"); 
		
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserId");
		List<Payment> userorder= paymentDAO.list(loggedInUserID);
		
		for(Payment row:userorder)
		{
			int number= product.getStock() - row.getQuantity();
			if(number>=0)
			{
				paymentDAO.save(row);
				product.setStock(number);
				productDAO.update(product);
			}
			else{
				mv= new ModelAndView("redirect:/proceed");
				return mv;
			}
			//delete the entire cart after purchasing
		}
		
		mv.addObject("finalPaymentDone", "Your payment was confirmed.");
		mv.addObject("orderPlaced", true);
	
		log.debug("End of the payment method");
		return mv;
	}
	
}
