package br.com.site.client.controller.bean;

public class SaborPizzaPizzaBean {

	private Integer idSaborPizzaPizza;
	private SaborPizzaBean idSabor;
	private PizzaBean idPizza;
	
	
	public Integer getIdSaborPizzaPizza() {
		return idSaborPizzaPizza;
	}
	public void setIdSaborPizzaPizza(Integer idSaborPizzaPizza) {
		this.idSaborPizzaPizza = idSaborPizzaPizza;
	}
	public SaborPizzaBean getIdSabor() {
		return idSabor;
	}
	public void setIdSabor(SaborPizzaBean idSabor) {
		this.idSabor = idSabor;
	}
	public PizzaBean getIdPizza() {
		return idPizza;
	}
	public void setIdPizza(PizzaBean idPizza) {
		this.idPizza = idPizza;
	}
	@Override
	public String toString() {
		return "Sabor: " + idSabor.getNome();
	}
	
	
}
