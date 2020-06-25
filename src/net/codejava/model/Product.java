package net.codejava.model;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity
@Table(name = "Products")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "productname")
	private String productname;

	@Column(name = "price")
	private int price;

	@Lob
	@Column(name = "img")
	private byte[] img;
	
//	
//	@Column(name = "category")
//	private long idcategory;

	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;
	
	

	public Product(Product product) {
	super();
	this.id = product.getId();
	this.productname = product.getProductname();
	this.price = product.getPrice();
	this.img = product.getImg();
}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + ", price=" + price + ", img="
				+ Arrays.toString(img) + "]";
	}

	

	public Product(long id, String productname, int price, byte[] img, long idcategory, Category category) {
		super();
		this.id = id;
		this.productname = productname;
		this.price = price;
		this.img = img;
//		this.idcategory = idcategory;
		this.category = category;
	}

	public Product() {
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

//	public long getIdcategory() {
//		return idcategory;
//	}
//
//	public void setIdcategory(long idcategory) {
//		this.idcategory = idcategory;
//	}


}
