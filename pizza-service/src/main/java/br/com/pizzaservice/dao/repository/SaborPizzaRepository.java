package br.com.pizzaservice.dao.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.pizzaservice.dao.entity.SaborPizza;

@Component
public class SaborPizzaRepository extends GenericDao<SaborPizza, Integer> {

	private static final long serialVersionUID = 1L;

}
