package br.com.loja.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the item_pedido database table.
 * 
 */
@Entity
@Table(name = "item_pedido")
@NamedQuery(name = "ItemPedido.findAll", query = "SELECT i FROM ItemPedido i")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1792390858009965292L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "preco_total")
	private double precoTotal;

	@Column(name = "preco_unitario")
	private double precoUnitario;

	private int quantidade;

	// bi-directional many-to-one association to Pedido
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	// bi-directional many-to-one association to Produto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public ItemPedido() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrecoTotal() {
		return this.precoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public double getPrecoUnitario() {
		return this.precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}