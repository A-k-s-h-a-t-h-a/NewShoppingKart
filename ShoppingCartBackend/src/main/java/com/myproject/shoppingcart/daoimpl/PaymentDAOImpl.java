package com.myproject.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppingcart.dao.PaymentDAO;
import com.myproject.shoppingcart.domain.Payment;
import com.myproject.shoppingcart.domain.Product;

@Transactional
@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO{


	@Autowired
	private Payment payment;
	
	@Autowired
	private SessionFactory sessionFactory;

	Logger log= LoggerFactory.getLogger(PaymentDAOImpl.class);
	
	public boolean save(Payment payment) {
		
		log.debug("Starting of the save method");
		try{
		sessionFactory.getCurrentSession().save(payment);
		log.debug("Ending of the save method");
		return true;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Payment payment) {

		log.debug("Starting of the save method");
		try{
		sessionFactory.getCurrentSession().update(payment);
		log.debug("Ending of the save method");
		return true;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Payment> list(String id) {
		log.debug("Starting and ending of the list method");
		return sessionFactory.getCurrentSession().createQuery("from Payment").list();
	}

}
