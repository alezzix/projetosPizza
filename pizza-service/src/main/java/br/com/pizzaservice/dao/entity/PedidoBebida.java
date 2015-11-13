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
@Table(name = "pedidobebida")
@XmlRootElement
public class PedidoBebida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idPedidoBebida;
	
	@ManyToOne
	@JoinColumn(name = "idPedido")
	private Pedido idPedido;
	
	@ManyToOne
	@JoinColumn(name = "idBebida")
	private Bebida idBebida;
	
	@Column
	private Integer quantidade;

	public Integer getIdPedidoBebida() {
		return idPedidoBebida;
	}

	public void setIdPedidoBebida(Integer idPedidoBebida) {
		this.idPedidoBebida = idPedidoBebida;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	public Bebida getIdBebida() {
		return idBebida;
	}

	public void setIdBebida(Bebida idBebida) {
		this.idBebida = idBebida;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
