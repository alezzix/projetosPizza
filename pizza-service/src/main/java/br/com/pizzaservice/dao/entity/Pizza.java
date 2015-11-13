package br.com.pizzaservice.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pizza")
@XmlRootElement
public class Pizza implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8472284159704821403L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idPizza;
	
	@Column
	private Double maiorVlr;

	
	public Integer getIdPizza() {
		return idPizza;
	}
	public void setIdPizza(Integer idPizza) {
		this.idPizza = idPizza;
	}
	public Double getMaiorVlr() {
		return maiorVlr;
	}
	public void setMaiorVlr(Double maiorVlr) {
		this.maiorVlr = maiorVlr;
	}
	

}
