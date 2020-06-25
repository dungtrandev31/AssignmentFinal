package net.codejava.cart;

import net.codejava.model.Product;
import java.util.Arrays;

public class ProductInfo {
	private long id;
	private String productname;
	private int price;
	private byte[] img;
	
	
public ProductInfo(Product product) {
		this.id= product.getId();
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + ", price=" + price + ", img="
				+ Arrays.toString(img) + "]";
	}
	public ProductInfo(long id, String productname, int price, byte[] img) {
		super();
		this.id = id;
		this.productname = productname;
		this.price = price;
		this.img = img;
	}
	public ProductInfo() {
	}

}
