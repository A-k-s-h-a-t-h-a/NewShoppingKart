package com.myproject.shoppingcart.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//whenever it scans all the classes under a particular package, creates instance of this class

@Component 										//to create instance, same as class name but first letter small
@Entity 										//to specify it is not normal class- it is database entry
@Table(name="user") 							//to specify to which database table we need to map this class
public class User {

	@Id 										//to specify that it is a primary key
	private String emailID;
	private String fullname;
	@Column(name="password") 					//to specify to map to a certain column in database table
	private String pwd;
	private String mobile;
	private String role;
	private Date registeredDate;
	private String state;
	
	private String address;
	private String pincode;
	
	private boolean status;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date date) {
		this.registeredDate = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
