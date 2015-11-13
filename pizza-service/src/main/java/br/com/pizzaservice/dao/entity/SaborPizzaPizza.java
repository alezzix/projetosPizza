package br.com.pizzaservice.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "sabor_pizza_pizza")
@XmlRootElement
public class SaborPizzaPizza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3229314653729795597L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idSaborPizzaPizza;
	
	

	@ManyToOne
	@JoinColumn(name = "idSabor")
	private SaborPizza idSabor;
	
	@ManyToOne
	@JoinColumn(name = "idPizza")
	private Pizza idPizza;

	public Integer getIdSaborPizzaPizza() {
		return idSaborPizzaPizza;
	}

	public void setIdSaborPizzaPizza(Integer idSaborPizzaPizza) {
		this.idSaborPizzaPizza = idSaborPizzaPizza;
	}

	public SaborPizza getIdSabor() {
		return idSabor;
	}

	public void setIdSabor(SaborPizza idSabor) {
		this.idSabor = idSabor;
	}

	public Pizza getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(Pizza idPizza) {
		this.idPizza = idPizza;
	}

	

	
}
