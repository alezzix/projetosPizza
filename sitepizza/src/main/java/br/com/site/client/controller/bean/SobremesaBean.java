package br.com.site.client.controller.bean;

public class SobremesaBean {

	private Integer idSobremesa;
	private String nome;
	private Double preco;
	private Integer quantidade;

	public Integer getIdSobremesa() {
		return idSobremesa;
	}

	public void setIdSobremesa(Integer idSobremesa) {
		this.idSobremesa = idSobremesa;
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
