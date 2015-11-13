package br.com.site.client.controller.bean;

public class SaborPizzaBean {
	
	private Integer idSabor;
	private String nome;
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
		return nome+" - R$"+preco;
	}
	
	

}
