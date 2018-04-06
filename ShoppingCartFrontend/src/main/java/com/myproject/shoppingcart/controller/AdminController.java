package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.dao.SupplierDAO;
import com.myproject.shoppingcart.daoimpl.UserDAOImpl;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.Product;
import com.myproject.shoppingcart.domain.Supplier;

@Controller
public class AdminController {

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Product product;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Supplier supplier;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private Category category;
	
	Logger log= LoggerFactory.getLogger(UserDAOImpl.class);
	
	@ModelAttribute("category")
	public Category creatingcat()
	{
		return new Category();
	}
	
	@GetMapping("/managecategories")
	public ModelAndView adminClickCategories()
	{
		ModelAndView mv= new ModelAndView("Home");
		
		String loggedInUserId= (String)httpSession.getAttribute("loggedInUserId"); //check if logged in or not
		if (loggedInUserId==null)
		{
			mv.addObject("errorMessage", "Please login to do this operation");
			return mv;
		}
		
		boolean isAdmin= (Boolean)httpSession.getAttribute("isAdmin"); //if logged in check if admin or not
		if (isAdmin==false)
		{
			mv.addObject("errorMessage", "You are not authorized to do this operation");
			return mv;
		}
		
		log.debug("Starting of the method adminClickedCategories");
		
		mv.addObject("isadminClickCategories", true);
		category=(Category) httpSession.getAttribute("category");
		mv.addObject("category",category);
		List<Category> categories= categoryDAO.list();					//fetches all categories again
		httpSession.setAttribute("categories", categories);
		
		log.debug("Ending of the method adminClickedCategories");
		return mv;
	}
	
	@GetMapping("/managesuppliers")
	public ModelAndView adminClickSuppliers()
	{
		log.debug("Start of the method AdminClickedSuppliers");
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isadminClickSuppliers", true);
		List<Supplier> suppliers= supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		
		log.debug("End of the method AdminClickedSuppliers");
		return mv;
	}
	
	@GetMapping("/manageproducts")
	public ModelAndView adminClickProducts()
	{
		log.debug("Start of the method AdminClickedProducts");
		ModelAndView mv= new ModelAndView("Home");
		mv.addObject("isadminClickProducts", true);
		List<Product> products= productDAO.list();		//to fetch the table
		List<Category> categories= categoryDAO.list();	//to fetch in the dropdown and navbar
		List<Supplier> suppliers= supplierDAO.list();	//same

		httpSession.setAttribute("products", products);
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		
		log.debug("End of the method AdminClickedProducts");
		return mv;
	}
}
