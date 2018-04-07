package com.myproject.shoppingcart;						/*To check if impl i.e logic works properly or not, we had only written it, 
														except we can't test validate function because we cannot click anything in the test case*/

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myproject.shoppingcart.dao.UserDAO;
import com.myproject.shoppingcart.domain.User;

public class UserDAOTestCase {

	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static User user;								// ?
	
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO= (UserDAO)context.getBean("userDAO");
		user= (User)context.getBean("user");
	}
	
	@Test
	public void saveUserTestCase()
	{
		user.setEmailID("jaskaran@gmail.com");
		user.setMobile("9871201670");
		user.setFullname("Jaskaran Singh");
		user.setPwd("jas@123");
		boolean status= userDAO.save(user);
		assertEquals("save user test case", true, status);
		
		user.setEmailID("kiran11@gmail.com");
		user.setMobile("9472198218");
		user.setFullname("Kiran Rao");
		user.setPwd("kir@123");
		boolean status1= userDAO.save(user);
		assertEquals("save user test case", true, status1);
		
		user.setEmailID("michelle@gmail.com");
		user.setMobile("8812836718");
		user.setFullname("Michelle Gomes");
		user.setPwd("mic@123");
		boolean status2= userDAO.save(user);
		assertEquals("save user test case", true, status2);
}
	
	@Test
	public void updateUserTestCase()
	{
		user.setEmailID("jaskaran1@gmail.com");
		user.setMobile("8791280897");
		boolean status= userDAO.update(user);
		assertEquals("update user test case", true, status); 					// ?
	}
	
	@Test
	public void getUserSuccessTestCase()
	{
		user= userDAO.get("jaskaran2@gmail.com");
		assertNotNull("get user test case", user);
	}
	@Test
	public void getUserFailureTestCase()
	{
		user= userDAO.get("jaya@gmail3.com");
		assertNull("get user test case fails", user);
	}
	
	@Test
	public void deleteUserSuccessTestCase()	
	{
		boolean status= userDAO.delete("michelle1@gmail.com");
		assertEquals("delete user success test case", true, status);
	}
	@Test
	public void deleteUserFailureTestCase()	
	{
		boolean status= userDAO.delete("mahesh2@gmail.com");
		assertEquals("delete user failure test case", false, status);
	}
	
	@Test
	public void getAllUsersTestCase()
	{
		List<User> users= userDAO.list();							//call userDAO by object
		assertEquals("get all users", 3, users.size());
	}
	
	@Test
	public void validateCredentialsTestCase()
	{
		user= userDAO.validate("jaskaran@gmail.com", "jas@123");
		assertNotNull("validate user test case", user);
	}
}
