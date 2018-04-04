package com.myproject.shoppingcart;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfiguration {

	public static void main(String[] args) {
																		
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(); //because we used annotations above the domain class
		
		context.scan("com.myproject");		//scans package & checks if any classes with annotation in the package
		context.refresh();					//clears context & creates new instances of any classes with annotation in com.niit package
		context.getBean("user");			//get instance of user class if available in factory else error
//		context.getBean("employee");
		
		System.out.println("Successfully created instance");
	}
}
