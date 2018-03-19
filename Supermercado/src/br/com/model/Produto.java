package br.com.model;

public class Produto {

	private String codigo;
	private String descricao;
	private String fabricante;
	private float preco;

	// Metodo ToString
	@Override
	public String toString() {
		return "Produtos [codigo=" + codigo + ", descricao=" + descricao + ", fabricante=" + fabricante + ", preco="
				+ preco + "]";
	}

	// Metodo Construtor
	public Produto(String codigo, String descricao, String fabricante, float preco) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.preco = preco;
	}

	// Metodos Get's e Set's
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

}
