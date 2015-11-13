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
@Table(name = "pedidosobremesa")
@XmlRootElement
public class PedidoSobremesa implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private Integer idPedidoSobremesa;
		
		@ManyToOne
		@JoinColumn(name = "idPedido")
		private Pedido idPedido;
		
		@ManyToOne
		@JoinColumn(name = "idSobremesa")
		private Sobremesa idSobremesa;
		
		@Column
		private Integer quantidade;

		public Integer getIdPedidoSobremesa() {
			return idPedidoSobremesa;
		}

		public void setIdPedidoSobremesa(Integer idPedidoSobremesa) {
			this.idPedidoSobremesa = idPedidoSobremesa;
		}

		public Pedido getIdPedido() {
			return idPedido;
		}

		public void setIdPedido(Pedido idPedido) {
			this.idPedido = idPedido;
		}

		public Sobremesa getIdSobremesa() {
			return idSobremesa;
		}

		public void setIdSobremesa(Sobremesa idSobremesa) {
			this.idSobremesa = idSobremesa;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}
		
		

}
