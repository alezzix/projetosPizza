package br.com.site.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

import br.com.site.client.AddSaborPizzaPizzaClient;
import br.com.site.client.BebidaClient;
import br.com.site.client.BuscaSaboresPizzaClient;
import br.com.site.client.PedidoClient;
import br.com.site.client.PizzaClient;
import br.com.site.client.SobremesaClient;
import br.com.site.client.controller.bean.BebidaBean;
import br.com.site.client.controller.bean.PizzaBean;
import br.com.site.client.controller.bean.SaborPizzaBean;
import br.com.site.client.controller.bean.SaborPizzaPizzaBean;
import br.com.site.client.controller.bean.SobremesaBean;

@ManagedBean
@ViewScoped
public class SiteController {

	private List<SaborPizzaBean> listaSabores = new ArrayList<SaborPizzaBean>();
	private List<SaborPizzaBean> listaSaboresTarget = new ArrayList<SaborPizzaBean>();
	private DualListModel<SaborPizzaBean> dualListaSabores;

	private List<BebidaBean> listaBebidas = new ArrayList<BebidaBean>();
	private List<BebidaBean> listaBebidasTarget = new ArrayList<BebidaBean>();
	private DualListModel<BebidaBean> dualListaBebidas;
	private List<BebidaBean> listaBebidasSelecionadas = new ArrayList<BebidaBean>();

	private List<SobremesaBean> listaSobremesas = new ArrayList<SobremesaBean>();
	private List<SobremesaBean> listaSobremesasTarget = new ArrayList<SobremesaBean>();
	private DualListModel<SobremesaBean> dualListaSobremesas;
	private List<SobremesaBean> listaSobremesasSelecionadas = new ArrayList<SobremesaBean>();

	private List<SaborPizzaPizzaBean> listaSaborPizzaPizzaBean;

	private Map<Integer, List<SaborPizzaBean>> myMap;
	private List<Entry<Integer, List<SaborPizzaBean>>> entriesMap;

	private Double total = 0.0d;

	private static final Logger logger = Logger.getLogger(SiteController.class);

	@PostConstruct
	public void init() throws Exception {

		listaSobremesas = listarSobremesas();
		listaBebidas = listarBebidas();
		listaSabores = listarSabores();

		dualListaSabores = new DualListModel<SaborPizzaBean>(listaSabores,
				listaSaboresTarget);
		dualListaBebidas = new DualListModel<BebidaBean>(listaBebidas,
				listaBebidasTarget);
		dualListaSobremesas = new DualListModel<SobremesaBean>(listaSobremesas,
				listaSobremesasTarget);

		atualizaMyMap();

		// PizzaClient client = new PizzaClient();
		// client.deletePizza();
	}

	public List<SaborPizzaBean> listarSabores() {
		try {
			BuscaSaboresPizzaClient busca = new BuscaSaboresPizzaClient();
			return busca.buscaSaboresPizza();
		} catch (Exception e) {
			logger.error("Erro ao chamar o client sabores: " + e);
			return null;
		}
	}

	public List<BebidaBean> listarBebidas() {
		try {
			BebidaClient client = new BebidaClient();
			return client.buscaBebidas();
		} catch (Exception e) {
			logger.error("Erro ao chamar o client bebidas: " + e);
			return null;
		}
	}

	public List<SobremesaBean> listarSobremesas() {
		try {
			SobremesaClient client = new SobremesaClient();
			return client.buscaSobremesas();
		} catch (Exception e) {
			logger.error("Erro ao chamar o client sobremesas: " + e);
			return null;
		}
	}

	public void adicionarPizza() {
		if (dualListaSabores.getTarget().size() > 4) {
			FacesContext.getCurrentInstance().addMessage(
					"newPassword1",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Escolha no máximo 4 sabores.", null));

		} else {

			// maior valor:
			Double max = 0.0;
			for (SaborPizzaBean vlr : dualListaSabores.getTarget()) {
				if (vlr.getPreco() > max) {
					max = vlr.getPreco();
				}
			}
			// total = total+max;
			// System.out.println("Valor maior: "+max);

			// add Pizza:
			PizzaClient clientPizza = new PizzaClient();
			try {
				Integer idPizza = Integer.valueOf(clientPizza.addPizza(max));

				// Adiciona na saborPizzaPizza
				for (SaborPizzaBean vlr : dualListaSabores.getTarget()) {
					AddSaborPizzaPizzaClient clientSaborPizzaPizzaClient = new AddSaborPizzaPizzaClient();
					clientSaborPizzaPizzaClient.addSaborPizzaPizza(idPizza,
							vlr.getIdSabor());
				}

				// tabela total:
				atualizaMyMap();

			} catch (Exception e) {
				logger.error("Erro ao chamar o client: " + e);
			}
		}
	}

	public void adicionarBebida() throws Exception {

		listaBebidasSelecionadas = new ArrayList<BebidaBean>();

		for (BebidaBean vlr : dualListaBebidas.getTarget()) {
			vlr.setQuantidade(1);
			listaBebidasSelecionadas.add(vlr);
		}
		atualizaTotal();

		listaBebidasSelecionadas.get(0).getIdBebida();
	}

	public void adicionarSobremesa() throws Exception {

		listaSobremesasSelecionadas = new ArrayList<SobremesaBean>();

		for (SobremesaBean vlr : dualListaSobremesas.getTarget()) {
			vlr.setQuantidade(1);
			listaSobremesasSelecionadas.add(vlr);
		}
		atualizaTotal();
	}

	public void atualizaMyMap() throws Exception {

		PizzaClient clientPizza2 = new PizzaClient();
		listaSaborPizzaPizzaBean = new ArrayList<SaborPizzaPizzaBean>();
		listaSaborPizzaPizzaBean = clientPizza2.buscaPizzasPizza();

		myMap = new HashMap<Integer, List<SaborPizzaBean>>();
		for (SaborPizzaPizzaBean spp : listaSaborPizzaPizzaBean) {
			if (myMap.containsKey(spp.getIdPizza().getIdPizza())) {
				myMap.get(spp.getIdPizza().getIdPizza()).add(spp.getIdSabor());
			} else {
				List<SaborPizzaBean> listaSabor = new ArrayList<SaborPizzaBean>();
				listaSabor.add(spp.getIdSabor());
				myMap.put(spp.getIdPizza().getIdPizza(), listaSabor);
			}

		}
		System.out.println("myMap" + myMap.keySet());

		entriesMap = new ArrayList<>(myMap.entrySet());

		atualizaTotal();

	}

	public void atualizaTotal() throws Exception {

		PizzaClient clientPizza = new PizzaClient();
		List<PizzaBean> list = new ArrayList<PizzaBean>();
		list = clientPizza.buscaPizzas();
		total = new Double(0.0d);

		// lista pizzas
		for (PizzaBean pi : list) {
			total = total + pi.getMaiorVlr();
		}

		// lista bebidas
		for (BebidaBean vlr : listaBebidasSelecionadas) {
			total = total + vlr.getPreco() * vlr.getQuantidade();
		}

		// lista sobremesas
		for (SobremesaBean vlr : listaSobremesasSelecionadas) {
			total = total + vlr.getPreco() * vlr.getQuantidade();
		}
	}

	public void excluirPizza(Entry e) throws Exception {

		PizzaClient client = new PizzaClient();

		Integer chavePizza = (Integer) e.getKey();
		client.excluirPizza(chavePizza);
		entriesMap.remove(e);
		atualizaTotal();
	}

	public void excluirBebida(BebidaBean beb) throws Exception {
		listaBebidasSelecionadas.remove(beb);
		atualizaTotal();
	}

	public void excluirSobremesa(SobremesaBean sob) throws Exception {
		listaSobremesasSelecionadas.remove(sob);
		atualizaTotal();
	}

	public void fecharPedido() throws Exception {
		
		if (total.equals(0.0d)){
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Selecione ao menos um item.", null));
		}else{
			PedidoClient pedido = new PedidoClient();

		Integer idPedido = Integer.valueOf(pedido.addPedido(total));

		// pedido pizza
		for (Entry ee : entriesMap) {
			pedido.addPedidoPizza(idPedido, (Integer) ee.getKey());
		}
		// pedido bebida
		for (BebidaBean bb : listaBebidasSelecionadas) {
			pedido.addPedidoBebida(idPedido, bb.getIdBebida(),
					bb.getQuantidade());
		}

		// pedido sobremesa
		for (SobremesaBean ss : listaSobremesasSelecionadas) {
			pedido.addPedidoSobremesa(idPedido, ss.getIdSobremesa(),
					ss.getQuantidade());
		}

		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Pedido fechado com sucesso.", null));
	}

	}

	public DualListModel<SaborPizzaBean> getDualListaSabores() {
		return dualListaSabores;
	}

	public void setDualListaSabores(
			DualListModel<SaborPizzaBean> dualListaSabores) {
		this.dualListaSabores = dualListaSabores;
	}

	public DualListModel<BebidaBean> getDualListaBebidas() {
		return dualListaBebidas;
	}

	public void setDualListaBebidas(DualListModel<BebidaBean> dualListaBebidas) {
		this.dualListaBebidas = dualListaBebidas;
	}

	public List<Entry<Integer, List<SaborPizzaBean>>> getEntriesMap() {
		return entriesMap;
	}

	public void setEntriesMap(
			List<Entry<Integer, List<SaborPizzaBean>>> entriesMap) {
		this.entriesMap = entriesMap;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<BebidaBean> getListaBebidasSelecionadas() {
		return listaBebidasSelecionadas;
	}

	public void setListaBebidasSelecionadas(
			List<BebidaBean> listaBebidasSelecionadas) {
		this.listaBebidasSelecionadas = listaBebidasSelecionadas;
	}

	public List<SobremesaBean> getListaSobremesasSelecionadas() {
		return listaSobremesasSelecionadas;
	}

	public void setListaSobremesasSelecionadas(
			List<SobremesaBean> listaSobremesasSelecionadas) {
		this.listaSobremesasSelecionadas = listaSobremesasSelecionadas;
	}

	public DualListModel<SobremesaBean> getDualListaSobremesas() {
		return dualListaSobremesas;
	}

	public void setDualListaSobremesas(
			DualListModel<SobremesaBean> dualListaSobremesas) {
		this.dualListaSobremesas = dualListaSobremesas;
	}

}
