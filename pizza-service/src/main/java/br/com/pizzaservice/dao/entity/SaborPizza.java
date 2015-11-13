package br.com.pizzaservice.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "sabores")
@XmlRootElement//(name="SaborPizzaBean")
public class SaborPizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idSabor;

	@Column
	private String nome;
	
	@Column
	private Double preco;

	

	

	public Integer getIdSabor() {
		return idSabor;
	}

	public void setIdSabor(Integer idSabor) {
		this.idSabor = idSabor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "SaborPizza [idSabor=" + idSabor + ", nome=" + nome + ", preco="
				+ preco + "]";
	}
	
}
