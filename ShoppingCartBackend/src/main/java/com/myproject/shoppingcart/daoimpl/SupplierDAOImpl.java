package com.myproject.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.shoppingcart.dao.SupplierDAO;
import com.myproject.shoppingcart.domain.Category;
import com.myproject.shoppingcart.domain.Supplier;

@Transactional
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired 
	private Supplier supplier;
	
	public boolean save(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().update(supplier);
			return false;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Supplier get(String supplier_id) {
		return sessionFactory.getCurrentSession().get(Supplier.class, supplier_id);
	}

	public List<Supplier> list() {
		//return sessionFactory.getCurrentSession().createQuery("from Supplier").list();
		return(List<Supplier>)sessionFactory.getCurrentSession().createCriteria(Supplier.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public boolean delete(String supplier_id) {
		try{
			supplier= get(supplier_id);
			if (supplier== null){
				return false;
			}
			sessionFactory.getCurrentSession().delete(supplier);
			return true;
		}
		catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}
}
