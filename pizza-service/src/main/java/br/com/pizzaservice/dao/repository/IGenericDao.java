package br.com.pizzaservice.dao.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
//@Component
public interface IGenericDao<T, ID extends Serializable> {

	void setSessionFactory(SessionFactory sessionFactory);

	Session getSession();

	ID save(T entity);

	void saveOrUpdate(T entity);

	void update(T entity);
	
	T merge(T entity);

	void delete(T entity);
	
	T findById(ID id);

	List<T> findAll();

	List<T> findAll(int firstResult, int maxResults);

	List<T> findLimitedByProperty(String property, Object value, int firstResult, int maxResults);
	
	List<T> findAllByProperty(String property, Object value);
	
	T findUniqueResultByProperty(String property, Object value);
	
}