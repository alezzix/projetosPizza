package br.com.pizzaservice.dao.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//@Component
public abstract class GenericDao<T, K extends Serializable> implements IGenericDao<T, K>, Serializable {

	private static final long serialVersionUID = 8815356148110725855L;

	private Class<T> entityClass;
	
	private static final String UNCHECKED = "unchecked";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//private Session session;

	@SuppressWarnings(UNCHECKED)
	public GenericDao() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		System.out.println("sessionFactory"+sessionFactory.getCache());
		System.out.println("thisss"+sessionFactory.getCache());
		this.sessionFactory = sessionFactory;
	}
	

	public Session getSession() {
		
		return	sessionFactory.getCurrentSession();
			
	}
	
	@SuppressWarnings(UNCHECKED)
	public List<T> findAll() {
		List<T> list = null;
		try {
			Criteria criteria = getSession().createCriteria(entityClass);
			list = (List<T>) criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return list;
	}

	@SuppressWarnings(UNCHECKED)
	public K save(T entity) {
		try {
			//if(!getSession().getTransaction().isActive()){
			//getSession().beginTransaction();
			//}
			K id = (K) getSession().save(entity);
			//getSession().getTransaction().commit();
			System.out.println(id);
			return id;
		} catch (HibernateException ex) {
			System.out.println("Erro: "+ex);
		//	getSession().getTransaction().rollback();
			throw new DaoException(ex);
		}
	}

	public void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
		} catch (HibernateException ex) {
			System.out.println(ex);
			throw new DaoException(ex);
		}
	}

	public void update(T entity) {
		try {
			getSession().clear();
			getSession().update(entity);
			getSession().flush();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
	}

	@SuppressWarnings(UNCHECKED)
	public T merge(T entity) {
		T t = null;
		try {
			t = (T) getSession().merge(entity);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return t;
	}

	public void delete(T entity) {
		try {
			getSession().delete(entity);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
	}
	
	public void deleteAll() {
		try {
			getSession().createQuery("Delete from SaborPizzaPizza").executeUpdate();
			getSession().createQuery("Delete from SaborPizza").executeUpdate();
			getSession().createQuery("Delete from Pizza").executeUpdate();
			
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
	}

	@SuppressWarnings(UNCHECKED)
	public T findById(K id) {
		T t = null;
		try {
			t = (T) getSession().get(entityClass, id);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return t;
	}
	
	@SuppressWarnings(UNCHECKED)
	public List<T> findByIdList(K id) {
		List<T> list = null;
		try {
			list = (List<T>) getSession().get(entityClass, id);
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return list;
	}

	@SuppressWarnings(UNCHECKED)
	public List<T> findAll(int firstResult, int maxResults) {
		List<T> list = null;
		try {
			Criteria criteria = getSession().createCriteria(entityClass);
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResults);
			list = (List<T>) criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return list;
	}
	
	@SuppressWarnings(UNCHECKED)
	public final List<T> findLimitedByProperty(String property, Object value, int firstResult, int maxResults) {
		List<T> list = null;
		try {
			Criteria criteria = getSession().createCriteria(entityClass)
				.add(Restrictions.eq(property, value));
			if(firstResult > 0) {
				criteria.setFirstResult(firstResult);
			}
			if(maxResults > 0) {
				criteria.setMaxResults(maxResults);
			}
			list = (List<T>) criteria.list();
		} catch (HibernateException ex) {
			throw new DaoException(ex);
		}
		return list;
	}
	
	public final List<T> findAllByProperty(String property, Object value) {
		return findLimitedByProperty(property, value, 0, 0);
	}
	
	public final T findUniqueResultByProperty(String property, Object value) {
		List<T> list = findLimitedByProperty(property, value, 0, 1);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			throw new DaoException("Data not found");
		}
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	

}