package com.myproject.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.shoppingcart.dao.SupplierDAO;
import com.myproject.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {

	@Autowired
	private SupplierDAO supplierDAO; 
	
	@Autowired
	private Supplier supplier; 
	
	@Autowired 
	HttpSession httpSession;
	
	@PostMapping("/supplier/save/")
	public ModelAndView saveSupplier(@RequestParam("supplier_id") String id, @RequestParam("name") String name, @RequestParam("address") String address)
	{
		ModelAndView mv= new ModelAndView("redirect:/managesuppliers");
		supplier.setSupplier_id(id);
		supplier.setName(name);
		supplier.setAddress(address);
		
		if (supplierDAO.save(supplier)){
			mv.addObject("suppliersuccess", "Supplier saved successfully");
			supplier.setSupplier_id("");
			supplier.setName("");
			supplier.setAddress("");
			
			List<Supplier> suppliers = supplierDAO.list();
			httpSession.setAttribute("suppliers", suppliers);	
		}
		else{
			mv.addObject("supplierfailure", "Couldn't save");
		}
		return mv;
	}
	
	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier)
	{
		ModelAndView mv= new ModelAndView("Home");
		if (supplierDAO.update(supplier)==true){
			mv.addObject("suppliersuccess", "Successfully updated");
		}
		else{
			mv.addObject("supplierfailure", "Failed to update");
		}
		return mv; 
	}

	@GetMapping("/Allsuppliers")
	public ModelAndView  getAllSuppliers()
	{
		ModelAndView mv= new ModelAndView("Home");
		List<Supplier> suppliers= supplierDAO.list();
		mv.addObject("suppliers", suppliers);
		return mv;
	}
	
	@GetMapping("/supplier/delete")
	public ModelAndView deleteSupplier(@RequestParam("id") String id)
	{
		ModelAndView mv= new ModelAndView("redirect:/managesuppliers");
		if (supplierDAO.delete(id)==true){
			mv.addObject("suppliersuccess", "Deleted");
		}
		else{
			mv.addObject("supplierfailure", "Not deleted");
		}
		return mv;
	}
	
	@GetMapping("/supplier/edit")
	public ModelAndView editsupplier(@RequestParam String id){
		ModelAndView mv= new ModelAndView("redirect:/managesuppliers");
		supplier= supplierDAO.get(id);
		httpSession.setAttribute("supplier", supplier);
		return mv;
	}	
	
//	@GetMapping("/supplier/get/{supplier_id}")
//	public ModelAndView getSupplier(@RequestParam("supplier_id") String id)
//	{
//		supplier= supplierDAO.get(id);
//		ModelAndView mv= new ModelAndView("Home");
//		return mv.addObject("supplier", supplier);
//	}
}
