package com.myproject.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppingcart.dao.CategoryDAO;
import com.myproject.shoppingcart.domain.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private Category category;
	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Category category) {
		try{
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Category category) {
		try{
		sessionFactory.getCurrentSession().update(category);
		return false;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	public Category get(String category_id) {
		return sessionFactory.getCurrentSession().get(Category.class, category_id);
	}

	public List<Category> list() {
		//return sessionFactory.getCurrentSession().createQuery("from Category").list();
		return(List<Category>)sessionFactory.getCurrentSession().createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public boolean delete(String category_id) {
		try{
			category= get(category_id);
			if (category== null){
				return false;}
			else
			sessionFactory.getCurrentSession().delete(category);
			return true;
		} 
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}	
}
