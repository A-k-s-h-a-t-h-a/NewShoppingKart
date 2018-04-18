package com.myproject.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppingcart.dao.CartDAO;
import com.myproject.shoppingcart.domain.Cart;

@Transactional
@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

	@Autowired
	private Cart cart;
	
	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private SessionFactory sessionFactory;

	Logger log= LoggerFactory.getLogger(CartDAOImpl.class);
	
	
	public boolean save(Cart cart) {
		log.debug("Starting of the save method");
		try {
			sessionFactory.getCurrentSession().save(cart);
			log.debug("Ending of the save method");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean update(Cart cart) {
		log.debug("Starting of the update method");
		try {
			cart.getEmailID();
			cart.setProductID(cart.getProductID());
			sessionFactory.getCurrentSession().update(cart);
			
			log.debug("Ending of the update method");
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public Cart get(int ID) {
		return sessionFactory.getCurrentSession().get(Cart.class, ID);
	}

	
//	public Cart get(String emailid, String productid) {
//		Cart cart= (Cart) sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("emailID", emailid)).add(Restrictions.eq("productID", productid));
//		cart.setQuantity(cart.getQuantity()+1);
//		cartDAO.update(cart);
//		return cart;
//	}

	
	public List<Cart> list(String emailid) {
		log.debug("Starting of the list method");
		return (List<Cart>) sessionFactory.getCurrentSession().createCriteria(Cart.class).add(Restrictions.eq("emailID", emailid)).list();
	}

	
	public boolean delete(int id) {
		log.debug("Starting of the delete method");
		try{
			cart= get(id);
			if (cart== null){
				return false;}
			else
			{sessionFactory.getCurrentSession().delete(cart);
			log.debug("Ending of the delete method");
			return true;}
		} 
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean update(String emailid){
		log.debug("Staring of the update by emailid method");
		log.debug("Going to place order of" + emailid);
		
		String hql= "Update cart set status='O' where emailid='" + emailid + "'";
		
		log.info("The given query is " + hql);
		
		try{
			sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
			log.debug("Ending of the update by emailid method");
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
