package net.codejava.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producttype")
public class ProductType implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String producttype;
	
	@Column(name ="id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "producttype")
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", producttype=" + producttype + "]";
	}
	public ProductType(long id, String producttype) {
		super();
		this.id = id;
		this.producttype = producttype;
	}
	public ProductType() {
	}
	
	
}
