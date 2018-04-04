package com.myproject.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproject.shoppingcart.domain.User;

@Repository("userDAO")
public interface UserDAO {
															//declare the methods
	public boolean save(User user);							//create new user
	
	public boolean update(User user);						//update the existing user
	
	public User get(String emailId);						//get the user details
	
	public boolean delete(String emailId);					//delete the user
	
	public List<User> list();								//gets all the users
		
	public User validate(String emailId, String password);	//validate if credentials are correct or not
	
}
