package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.domain.Category;

@Controller
public class CategoryController {

	@Autowired
	private CategoryDAO categoryDAO; 
	
	@Autowired
	private Category category; 
	
	@Autowired 
	HttpSession httpSession;
	
	@PostMapping("/category/save/")
	public ModelAndView saveCategory(@RequestParam("category_id") String id, @RequestParam("name") String name, 
																@RequestParam("description") String description){
		ModelAndView mv= new ModelAndView("redirect:/managecategories");
		
		category.setCategory_id(id);
		category.setName(name);
		category.setDescription(description);
		
		if (categoryDAO.save(category)){
			mv.addObject("categorysuccess", "Category saved successfully");
			category.setCategory_id("");
			category.setName("");
			category.setDescription("");
			
			List<Category> categories = categoryDAO.list();					//fetches all categories again
			httpSession.setAttribute("categories", categories);				//and sets to http session
		}
		else{
			mv.addObject("categoryerror", "Couldn't save");
		}
		return mv;
	}
		
	@PutMapping("/category/update/")
	public ModelAndView updateCategory(@ModelAttribute Category category)
	{
		ModelAndView mv= new ModelAndView("Home");
		
		if (categoryDAO.update(category)==true){
			mv.addObject("categorysuccess", "Successfully updated");
		}
		else{
			mv.addObject("categoryerror", "Failed to update");
		}
		return mv; 
	}
	
	@GetMapping("/Allcategories")
	public ModelAndView  getAllCategories()
	{
		ModelAndView mv= new ModelAndView("Home");
		List<Category> categories= categoryDAO.list();
		mv.addObject("categories", categories);
		return mv;
	}
	
	@GetMapping("/category/delete")
	public ModelAndView deleteCategory(@RequestParam("id") String id) //@RequestParam("category_id")
	{
		ModelAndView mv= new ModelAndView("redirect:/managecategories");
		if (categoryDAO.delete(id)==true){
			mv.addObject("categorysuccess", "Deleted");
		}
		else{
			mv.addObject("categoryerror", "Not deleted");
		}
		return mv;
	}
	
	@GetMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam("id") String id)
	{
		ModelAndView mv= new ModelAndView("redirect:/managecategories");
		category= categoryDAO.get(id);
		httpSession.setAttribute("category", category);
		return mv;
	}	

//	@GetMapping("/category/get/{category_id}")
//	public ModelAndView getCategory(@RequestParam("category_id") String id)
//	{
//		category= categoryDAO.get(id);
//		ModelAndView mv= new ModelAndView("Home");
//		return mv.addObject("category", category); //"category"
//	}
	
}
