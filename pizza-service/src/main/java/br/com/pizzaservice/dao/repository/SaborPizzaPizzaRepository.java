package br.com.pizzaservice.dao.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.pizzaservice.dao.entity.SaborPizzaPizza;

@Component
public class SaborPizzaPizzaRepository extends GenericDao<SaborPizzaPizza, Integer> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<SaborPizzaPizza> findByIdPizza(Integer idPizza){
		try{
		Criteria criteria = getSession().createCriteria(SaborPizzaPizza.class);
		criteria.add(Restrictions.eq("idPizza.idPizza", idPizza));
		
		return (List<SaborPizzaPizza>)criteria.list();
		}catch(Exception e){
			return null;
		}
		
	}
	
	public void deletaPizzas(Integer idPizza){

		try{
		Query query = getSession()
				.createQuery(
						"Delete from SaborPizzaPizza c where c.idPizza.idPizza = :idPizza");
		query.setParameter("idPizza", idPizza);
		
		query.executeUpdate();
		}catch(Exception e){

		}
	}
	

}
