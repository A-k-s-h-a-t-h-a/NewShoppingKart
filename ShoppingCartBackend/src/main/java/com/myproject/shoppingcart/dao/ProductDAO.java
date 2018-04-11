package com.myproject.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproject.shoppingcart.domain.Product;

@Repository("productDAO")
public interface ProductDAO {

	public boolean save(Product product);
	
	public boolean update(Product product);
	
	public Product get(String code);
	
	public List<Product> list();
	
	public boolean delete(String code);
	
	public List<Product> search(String searchString);
	
	public List<Product> search(String searchString, int maxPrice);
	
	public List<Product> search(String searchString, int minPrice, int maxPrice);
	
}
