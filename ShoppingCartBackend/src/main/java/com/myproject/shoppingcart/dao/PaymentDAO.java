package com.myproject.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproject.shoppingcart.domain.Payment;

@Repository("paymentDAO")
public interface PaymentDAO {

	public boolean save(Payment payment);
	
	public boolean update(Payment payment);

	public List<Payment> list(String id);
}
