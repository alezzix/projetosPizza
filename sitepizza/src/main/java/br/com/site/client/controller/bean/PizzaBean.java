package br.com.site.client.controller.bean;


public class PizzaBean {

	private Integer idPizza;
	
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

	@Override
	public String toString() {
		return "Pizza=" + idPizza;
	}
	

}
