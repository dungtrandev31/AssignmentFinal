package net.codejava.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderInfo")
public class OrderInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOrderInfo")
	private long id ;
	
	@ManyToOne
	@JoinColumn(name = "idproduct")
	private Product product;
	
	@Column(name = "quantity")
	private int quantity;
	
	
	@ManyToMany(mappedBy = "orderInfos",cascade = CascadeType.ALL)
	
	private Collection<Customer> customers;
	

	public OrderInfo(long id, Product product, int quantity, Collection<Customer> customers) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.customers = customers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderInfo(long id, Product product, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public OrderInfo() {
	
	}
	
	
	

}
