package br.com.pizzaservice.dao.repository;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.pizzaservice.dao.entity.Pizza;

@Component
public class PizzaRepository extends GenericDao<Pizza, Integer> {

	private static final long serialVersionUID = 1L;
	
	public void deletaPizza(Integer idPizza){

		try{
		Query query = getSession()
				.createQuery(
						"Delete from Pizza c where c.idPizza = :idPizza");
		query.setParameter("idPizza", idPizza);
		
		query.executeUpdate();
		}catch(Exception e){

		}
	}
	

}
