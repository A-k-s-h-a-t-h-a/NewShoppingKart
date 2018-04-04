package com.myproject.shoppingcart.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.dao.SupplierDAO;
import com.myproject.shoppingcart.domain.Product;
import com.myproject.util.FileUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO productDAO; 
	
	@Autowired
	private Product product; 
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private CategoryDAO categoryDAO; 
	
	@Autowired
	private SupplierDAO supplierDAO; 
	
	private static final String imageDirectory = "ShoppingCartImages";
	private static String rootPath = System.getProperty("catalina.home");

	@PostMapping("/product/save/")
	public ModelAndView saveProduct(@RequestParam("product_id") String id, @RequestParam("name") String name,
			@RequestParam("price") String price, @RequestParam("stock") String stock, 
			@RequestParam("category_id") String categoryID, @RequestParam("supplier_id") String supplierID, 
			@RequestParam("file") MultipartFile file){
		
		ModelAndView mv= new ModelAndView("redirect:/manageproducts"); 
		product.setProduct_id(id);
		product.setName(name);
		price= price.replace(", ", "");
		product.setPrice(Integer.parseInt(price));
		product.setStock(Integer.parseInt(stock));
//		product.setCategory(categoryDAO.get(categoryID));
//		product.setSupplier(supplierDAO.get(supplierID));
		product.setCat_id(categoryID);
		product.setSup_id(supplierID);
		
		if (productDAO.save(product)){
			mv.addObject("productsuccess", "Product saved successfully");
			product.setProduct_id("");
			product.setName("");
			product.setPrice(0);
			product.setStock(0);
			
			List<Product> products = productDAO.list();
			httpSession.setAttribute("products", products);
//			
//			if (FileUtil.fileCopyNIO(file, id+".png"))
//			{
//				mv.addObject("uploadmsg", "Product image successfully uploaded");
//			}
//			else
//			{
//				mv.addObject("uploadmsg", "Product image could not be uploaded");
//			}
		}
		else{
			mv.addObject("producterror", "Couldn't save");
		}
		return mv;
	}
    
	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product)
	{
		ModelAndView mv= new ModelAndView("Home");
		if (productDAO.update(product)==true){
			mv.addObject("productsuccess", "Successfully updated");
		}
		else{
			mv.addObject("producterror", "Failed to update");
		}
		return mv; 
	}
		
	@GetMapping("/allproducts")
	public ModelAndView getAllProducts()
	{
		ModelAndView mv= new ModelAndView("Home");
		List<Product> products= productDAO.list();
		mv.addObject("products", products);
		return mv;
	}
	
	@GetMapping("/product/delete") 
	public ModelAndView deleteProduct(@RequestParam("id") String id) 
	{
		ModelAndView mv= new ModelAndView("redirect:/manageproducts"); 
		if (productDAO.delete(id)==true){
			mv.addObject("productsuccess", "Deleted");
		}
		else{
			mv.addObject("producterror", "Not deleted");
		}
		return mv;
	}
	
	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam String id){
		ModelAndView mv= new ModelAndView("redirect:/manageproducts");
		product= productDAO.get(id);
		httpSession.setAttribute("selectedProduct", product); 
		return mv;
	}
	
	@GetMapping("/product/get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes)
	{
		ModelAndView mv= new ModelAndView("redirect:/");
		redirectAttributes.addFlashAttribute("selectedProduct", productDAO.get(id));
		redirectAttributes.addFlashAttribute("isUserSelectedProduct", true);
		redirectAttributes.addFlashAttribute("selectedProductImage", rootPath+ File.separator+ imageDirectory+ File.separator);
		return mv;
	}
}
