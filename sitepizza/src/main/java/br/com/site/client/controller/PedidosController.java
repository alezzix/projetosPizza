package br.com.site.client.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.site.client.PedidoClient;
import br.com.site.client.controller.bean.PedidoBean;

@ManagedBean
@ViewScoped
public class PedidosController {

	private List<PedidoBean> listaPedidos = new ArrayList<PedidoBean>();
	PedidoClient client = new PedidoClient();
	private static final Logger logger = Logger
			.getLogger(PedidosController.class);

	@PostConstruct
	public void init() throws Exception {

		listaPedidos = listarPedidos();
	}

	public List<PedidoBean> listarPedidos() {
		try {
			
			return client.buscaPedidos();
		} catch (Exception e) {
			logger.error("Erro ao chamar o client pedidos: " + e);
			return null;
		}
	}
	
	public void finalizar(PedidoBean pedido) {
		try {
			
		   client.finalizarPedido(pedido.getIdPedido());
		   listaPedidos = listarPedidos();
		} catch (Exception e) {
			logger.error("Erro ao chamar o client pedidos: " + e);
		}
	}

	public List<PedidoBean> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidoBean> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
	

}
