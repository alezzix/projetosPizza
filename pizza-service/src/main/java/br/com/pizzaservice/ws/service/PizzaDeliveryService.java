package br.com.pizzaservice.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET; //import da biblioteca jersey
import javax.ws.rs.Path; //import da biblioteca jersey 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; //import da biblioteca jersey
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.pizzaservice.dao.entity.Bebida;
import br.com.pizzaservice.dao.entity.Pedido;
import br.com.pizzaservice.dao.entity.PedidoBebida;
import br.com.pizzaservice.dao.entity.PedidoPizza;
import br.com.pizzaservice.dao.entity.PedidoSobremesa;
import br.com.pizzaservice.dao.entity.Pizza;
import br.com.pizzaservice.dao.entity.SaborPizza;
import br.com.pizzaservice.dao.entity.SaborPizzaPizza;
import br.com.pizzaservice.dao.entity.Sobremesa;
import br.com.pizzaservice.dao.repository.BebidaRepository;
import br.com.pizzaservice.dao.repository.PedidoBebidaRepository;
import br.com.pizzaservice.dao.repository.PedidoPizzaRepository;
import br.com.pizzaservice.dao.repository.PedidoRepository;
import br.com.pizzaservice.dao.repository.PedidoSobremesaRepository;
import br.com.pizzaservice.dao.repository.PizzaRepository;
import br.com.pizzaservice.dao.repository.SaborPizzaPizzaRepository;
import br.com.pizzaservice.dao.repository.SaborPizzaRepository;
import br.com.pizzaservice.dao.repository.SobremesaRepository;

@Component
@Produces(MediaType.TEXT_XML)
@Path("/delivery")
@Transactional
public class PizzaDeliveryService extends Application{

	
	@Autowired
	private SaborPizzaRepository saborPizzaRepository;	
	@Autowired
	private PizzaRepository pizzaRepository;	
	@Autowired
	private SaborPizzaPizzaRepository saborPizzaPizzaRepository;	
	@Autowired
	private BebidaRepository bebidaRepository;	
	@Autowired
	private SobremesaRepository sobremesaRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PedidoPizzaRepository pedidoPizzaRepository;
	@Autowired
	private PedidoBebidaRepository pedidoBebidaRepository;
	@Autowired
	private PedidoSobremesaRepository pedidoSobremesaRepository;


	
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/sabores")
	@XmlList
	public List<SaborPizza> saborPizza() {
		List<SaborPizza> lista = new ArrayList<SaborPizza>();
		lista = saborPizzaRepository.findAll();		
		if(lista.size()<1){			
			insertSabores();
		}	
		return saborPizzaRepository.findAll();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/bebidas")
	@XmlList
	public List<Bebida> listarBebidas() {
		List<Bebida> lista = new ArrayList<Bebida>();
		lista = bebidaRepository.findAll();		
		if(lista.size()<1){			
			insertBebidas();
		}	
		return bebidaRepository.findAll();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/sobremesas")
	@XmlList
	public List<Sobremesa> listarSobremesas() {
		List<Sobremesa> lista = new ArrayList<Sobremesa>();
		lista = sobremesaRepository.findAll();		
		if(lista.size()<1){			
			insertSobremesas();
		}	
		return sobremesaRepository.findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPizza/{maiorVlr}")
	public Integer addPizza(@PathParam("maiorVlr") Double maiorVlr) {
		Pizza pizza = new Pizza();
		pizza.setMaiorVlr(maiorVlr);
		return pizzaRepository.save(pizza);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addSaboresPizzaPizza/{idPizza}/{idSabor}")
	public void addPizzaSabores(@PathParam("idPizza") Integer idPizza,
			@PathParam("idSabor") Integer idSabor) {		
		
		SaborPizzaPizza saborPizzaPizza = new SaborPizzaPizza();			
		Pizza pizza = new Pizza();
		SaborPizza saborPizza = new SaborPizza();
		
		pizza = pizzaRepository.findById(idPizza);
		saborPizzaPizza.setIdPizza(pizza);
		
		saborPizza = saborPizzaRepository.findById(idSabor);
		saborPizzaPizza.setIdSabor(saborPizza);		
		
		saborPizzaPizza.setIdSaborPizzaPizza(1);
		
		saborPizzaPizzaRepository.save(saborPizzaPizza);
		
		List<SaborPizzaPizza> saborPizzaPizzaRepositoryList = new ArrayList<SaborPizzaPizza>();
		saborPizzaPizzaRepositoryList = saborPizzaPizzaRepository.findAll();
		for (SaborPizzaPizza sbs : saborPizzaPizzaRepositoryList) {
			System.out.println(sbs.getIdSabor().getNome());
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/listaPizzas")
	@XmlList
	public List<Pizza> listaPizzas() {

		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas = pizzaRepository.findAll();

		return pizzas;
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/listaSaborPizzasPizzas/{idPizza}")
	@XmlList
	public List<SaborPizzaPizza> listaSaborPizzasPizzas(@PathParam("idPizza") Integer idPizza) {
		
		List<SaborPizzaPizza> saborPizzasPizza = new ArrayList<SaborPizzaPizza>();				
		saborPizzasPizza = saborPizzaPizzaRepository.findByIdList(idPizza);
		
		return saborPizzasPizza;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/listaSaborPizzasPizzas1")
	@XmlList
	public List<SaborPizzaPizza> listaSaborPizzasPizzas() {
		
		List<SaborPizzaPizza> saborPizzasPizza = new ArrayList<SaborPizzaPizza>();				
		saborPizzasPizza = saborPizzaPizzaRepository.findAll();
		
		return saborPizzasPizza;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/limpar")
	@XmlList
	public void limpar() {

		List<Pizza> pizzas = new ArrayList<Pizza>();
		List<SaborPizza> sabor = new ArrayList<SaborPizza>();
		List<SaborPizzaPizza> pizzaspizz = new ArrayList<SaborPizzaPizza>();
		
		pizzas = pizzaRepository.findAll();
		sabor = saborPizzaRepository.findAll();
		pizzaspizz = saborPizzaPizzaRepository.findAll();
		
		for(SaborPizzaPizza sbpp :pizzaspizz){
			saborPizzaPizzaRepository.delete(sbpp);
		}
		for(SaborPizza sb : sabor){
			saborPizzaRepository.delete(sb);
		}
		
		for(Pizza p : pizzas){
			pizzaRepository.delete(p);
		}
	

	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/excluirPizza/{idPizza}")
	@XmlList
	public void excluirPizza(@PathParam("idPizza") Integer idPizza) {
		
		saborPizzaPizzaRepository.deletaPizzas(idPizza);
		pizzaRepository.deletaPizza(idPizza);
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/listaPedidos")
	@XmlList
	public List<Pedido> listaPedido() {
		
		List<Pedido> pedidos = new ArrayList<Pedido>();				
		pedidos = pedidoRepository.findAll();
		
		return pedidos;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/finalizarPedido/{idPedido}")
	public void finalizarPedido(@PathParam("idPedido") Integer idPedido) {
		Pedido pedido = new Pedido();
		pedidoRepository.findById(idPedido);
		pedido.setStatus("Finalizado");
	    pedidoRepository.saveOrUpdate(pedido);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPedido/{total}")
	public Integer addPedido(@PathParam("total") Double total) {
		Pedido pedido = new Pedido();
		pedido.setTotalPedido(total);
		pedido.setStatus("Pendente");
		return pedidoRepository.save(pedido);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPedidoPizza/{idPedido}/{idPizza}")
	public void addPedidoPizza(@PathParam("idPedido") Integer idPedido,
			@PathParam("idPizza") Integer idPizza) {

		PedidoPizza pedidoPizza = new PedidoPizza();
		Pizza pizza = new Pizza();
		Pedido pedido = new Pedido();
		
		pizza = pizzaRepository.findById(idPizza);
		pedido = pedidoRepository.findById(idPedido);
		
		pedidoPizza.setQuantidade(1);
		pedidoPizza.setIdPizza(pizza);
		pedidoPizza.setIdPedido(pedido);
		
	   pedidoPizzaRepository.save(pedidoPizza);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPedidoBebida/{idPedido}/{idBebida}/{qtd}")
	public void addPedidoBebida(@PathParam("idPedido") Integer idPedido,
			@PathParam("idBebida") Integer idBebida,
			@PathParam("qtd") Integer qtd) {

		PedidoBebida pedidoBebida = new PedidoBebida();
		Bebida bebida = new Bebida();
		Pedido pedido = new Pedido();
		
		bebida = bebidaRepository.findById(idBebida);
		pedido = pedidoRepository.findById(idPedido);
		
		pedidoBebida.setQuantidade(qtd);
		pedidoBebida.setIdBebida(bebida);
		pedidoBebida.setIdPedido(pedido);
		
	   pedidoBebidaRepository.save(pedidoBebida);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addPedidoSobremesa/{idPedido}/{idSobremesa}/{qtd}")
	public void addPedidoSobremesa(@PathParam("idPedido") Integer idPedido,
			@PathParam("idSobremesa") Integer idSobremesa,
			@PathParam("qtd") Integer qtd) {

		PedidoSobremesa pedidoSobremesa = new PedidoSobremesa();
		Sobremesa sobremesa = new Sobremesa();
		Pedido pedido = new Pedido();
		
		sobremesa = sobremesaRepository.findById(idSobremesa);
		pedido = pedidoRepository.findById(idPedido);
		
		pedidoSobremesa.setQuantidade(qtd);
		pedidoSobremesa.setIdSobremesa(sobremesa);
		pedidoSobremesa.setIdPedido(pedido);
		
	   pedidoSobremesaRepository.save(pedidoSobremesa);
	}

	public SaborPizzaRepository getSaborPizzaRepository() {
		return saborPizzaRepository;
	}


	public void setSaborPizzaRepository(SaborPizzaRepository saborPizzaRepository) {
		if(saborPizzaRepository!=null)
		this.saborPizzaRepository = saborPizzaRepository;
	}


	public PizzaRepository getPizzaRepository() {
		return pizzaRepository;
	}


	public void setPizzaRepository(PizzaRepository pizzaRepository) {
		if(pizzaRepository!=null)
		this.pizzaRepository = pizzaRepository;
	}


	public SaborPizzaPizzaRepository getSaborPizzaPizzaRepository() {
		return saborPizzaPizzaRepository;
	}


	public void setSaborPizzaPizzaRepository(
			SaborPizzaPizzaRepository saborPizzaPizzaRepository) {
		if(saborPizzaPizzaRepository!=null)
		this.saborPizzaPizzaRepository = saborPizzaPizzaRepository;
	}
	
	
	public BebidaRepository getBebidaRepository() {
		return bebidaRepository;
	}

	public void setBebidaRepository(BebidaRepository bebidaRepository) {
		if(bebidaRepository!=null)
		this.bebidaRepository = bebidaRepository;
	}

	
	public SobremesaRepository getSobremesaRepository() {
		return sobremesaRepository;
	}

	public void setSobremesaRepository(SobremesaRepository sobremesaRepository) {
		if(sobremesaRepository!=null)
		this.sobremesaRepository = sobremesaRepository;
	}
	
	

	public PedidoRepository getPedidoRepository() {
		return pedidoRepository;
	}

	public void setPedidoRepository(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public PedidoPizzaRepository getPedidoPizzaRepository() {
		return pedidoPizzaRepository;
	}

	public void setPedidoPizzaRepository(PedidoPizzaRepository pedidoPizzaRepository) {
		this.pedidoPizzaRepository = pedidoPizzaRepository;
	}

	public PedidoBebidaRepository getPedidoBebidaRepository() {
		return pedidoBebidaRepository;
	}

	public void setPedidoBebidaRepository(
			PedidoBebidaRepository pedidoBebidaRepository) {
		this.pedidoBebidaRepository = pedidoBebidaRepository;
	}

	public PedidoSobremesaRepository getPedidoSobremesaRepository() {
		return pedidoSobremesaRepository;
	}

	public void setPedidoSobremesaRepository(
			PedidoSobremesaRepository pedidoSobremesaRepository) {
		this.pedidoSobremesaRepository = pedidoSobremesaRepository;
	}

	public void insertBebidas(){
		
		Bebida coca = new Bebida();
		coca.setIdBebida(0);
		coca.setNome("Coca-Cola");
		coca.setPreco(2.5);
		bebidaRepository.save(coca);
		
		Bebida sprit = new Bebida();
		sprit.setIdBebida(0);
		sprit.setNome("Sprite");
		sprit.setPreco(2.4);
		bebidaRepository.save(sprit);
		
		Bebida fanta = new Bebida();
		fanta.setIdBebida(0);
		fanta.setNome("Fanta");
		fanta.setPreco(2.45);
		bebidaRepository.save(fanta);
		
		Bebida pepsi = new Bebida();
		pepsi.setIdBebida(0);
		pepsi.setNome("Pepsi");
		pepsi.setPreco(2.5);
		bebidaRepository.save(pepsi);
		
	
	}
	public void insertSobremesas(){
	
	Sobremesa pudim = new Sobremesa();
	pudim.setIdSobremesa(0);
	pudim.setNome("Pudim");
	pudim.setPreco(5.0);
	sobremesaRepository.save(pudim);
	
	Sobremesa mousse = new Sobremesa();
	mousse.setIdSobremesa(0);
	mousse.setNome("Mousse");
	mousse.setPreco(4.0);
	sobremesaRepository.save(mousse);
	
	Sobremesa bomba = new Sobremesa();
	bomba.setIdSobremesa(0);
	bomba.setNome("Bomba de chocolate");
	bomba.setPreco(6.0);
	sobremesaRepository.save(bomba);
	

}

	
	public void insertSabores(){
		
		SaborPizza saborCamarao = new SaborPizza();
		saborCamarao.setIdSabor(0);
		saborCamarao.setNome("Camarao");
		saborCamarao.setPreco(43.5);
		saborPizzaRepository.save(saborCamarao);
		
	
		SaborPizza saborCalabresa = new SaborPizza();
		saborCalabresa.setIdSabor(0);
		saborCalabresa.setNome("Calabresa");
		saborCalabresa.setPreco(35.0);
		saborPizzaRepository.save(saborCalabresa);
		
		SaborPizza saborMussarela = new SaborPizza();
		saborMussarela.setIdSabor(0);
		saborMussarela.setNome("Mussarela");
		saborMussarela.setPreco(30.0);
		saborPizzaRepository.save(saborMussarela);
		
		SaborPizza sabor4queijos = new SaborPizza();
		sabor4queijos.setIdSabor(0);
		sabor4queijos.setNome("4 Queijos");
		sabor4queijos.setPreco(32.5);
		saborPizzaRepository.save(sabor4queijos);
		
		SaborPizza saborMilho = new SaborPizza();
		saborMilho.setIdSabor(0);
		saborMilho.setNome("Milho");
		saborMilho.setPreco(34.5);
		saborPizzaRepository.save(saborMilho);
		
		
		SaborPizza saborPortuguesa = new SaborPizza();
		saborPortuguesa.setIdSabor(0);
		saborPortuguesa.setNome("Portuguesa");
		saborPortuguesa.setPreco(38.0);
		saborPizzaRepository.save(saborPortuguesa);
	}
	
	

}
