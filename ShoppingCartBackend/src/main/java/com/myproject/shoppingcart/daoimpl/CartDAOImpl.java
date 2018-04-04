package com.myproject.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	private SessionFactory sessionFactory;

	public boolean save(Cart cart) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(cart);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return false;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cart get(String cart_id) {
		return sessionFactory.getCurrentSession().get(Cart.class, cart_id);
	}

	public List<Cart> list(String emailid) {
		return (List<Cart>) sessionFactory.getCurrentSession().createCriteria(Cart.class)
				.add(Restrictions.eq("emailid", "emailid")).list();
	}

	public boolean delete(String id) {
		try{
			cart= get(id);
			if (cart== null){
				return false;}
			else
			{sessionFactory.getCurrentSession().delete(cart);
			return true;}
		} 
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
}
