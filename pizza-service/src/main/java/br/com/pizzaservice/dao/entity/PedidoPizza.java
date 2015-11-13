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
@Table(name = "pedidopizza")
@XmlRootElement
public class PedidoPizza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idPedidoPizza;
	
	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido idPedido;
	
	@ManyToOne
	@JoinColumn(name = "idPizza")
	private Pizza idPizza;
	
	@Column
	private Integer quantidade;

	public Integer getIdPedidoPizza() {
		return idPedidoPizza;
	}

	public void setIdPedidoPizza(Integer idPedidoPizza) {
		this.idPedidoPizza = idPedidoPizza;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	public Pizza getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(Pizza idPizza) {
		this.idPizza = idPizza;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
