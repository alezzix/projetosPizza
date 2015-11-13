package br.com.site.client.controller.bean;

public class BebidaBean {

	private Integer idBebida;
	private String nome;
	private Double preco;
	private Integer quantidade;
	
	
	public Integer getIdBebida() {
		return idBebida;
	}
	public void setIdBebida(Integer idBebida) {
		this.idBebida = idBebida;
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
	
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return nome+" - R$"+preco;
	}
	
	
}
