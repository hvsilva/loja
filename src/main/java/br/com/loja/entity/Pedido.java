package br.com.loja.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the pedido database table.
 * 
 */
@Entity
@NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
public class Pedido implements Serializable, AbstractEntity {

	private static final long serialVersionUID = -7856998490007127545L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Timestamp data;

	private double total;

	// bi-directional many-to-one association to ItemPedido
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedidos = new ArrayList<>();

	// bi-directional many-to-one association to Cliente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	public Pedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

//	public List<ItemPedido> getItemPedidos() {
//		List<ItemPedido> listaSegura = Collections.unmodifiableList(this.itemPedidos);
//		return listaSegura;
//	}

//	public void addItemPedido(ItemPedido itemPedido) {
//		this.itemPedidos.add(itemPedido);
//		itemPedido.setPedido(this);
//	}
	
	
	public List<ItemPedido> getItemPedidos() {
		return this.itemPedidos;
	}
	
	
	public ItemPedido addItemPedido(ItemPedido itemPedido) {
		getItemPedidos().add(itemPedido);
		itemPedido.setPedido(this);

		return itemPedido;
	}

	public ItemPedido removeItemPedido(ItemPedido itemPedido) {
		getItemPedidos().remove(itemPedido);
		itemPedido.setPedido(null);

		return itemPedido;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}