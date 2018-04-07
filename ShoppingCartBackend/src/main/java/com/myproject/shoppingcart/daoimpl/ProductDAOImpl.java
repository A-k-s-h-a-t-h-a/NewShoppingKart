package com.myproject.shoppingcart.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppingcart.dao.ProductDAO;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Product product;

	public boolean save(Product product) {
		try {
			product.setDate_created(new Date(System.currentTimeMillis()));
			
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return false;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Product get(String product_id) {
		return sessionFactory.getCurrentSession().get(Product.class, product_id);
	}

	public List<Product> list() {
//		return sessionFactory.getCurrentSession().createQuery("from Product").list();
		return(List<Product>)sessionFactory.getCurrentSession().createCriteria(Product.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public boolean delete(String product_id) {
		try{
			product= get(product_id);
			if (product== null){
				return false;}
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		catch (HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
}
