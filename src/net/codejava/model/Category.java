package net.codejava.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Category")
public class Category implements Serializable{
	 private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idcategory")
	private long idcategory;

	@Column(name = "idproducttype")
	private long idproducttype;
	
	@Column(name = "categoryname")
	private String categoryname;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Product> product;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<Product> getProducts() {
        return product;
    }
	
    
	public Category() {
	}

	public long getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(long idcategory) {
		this.idcategory = idcategory;
	}

	public long getIdproducttype() {
		return idproducttype;
	}

	public void setIdproducttype(long idproducttype) {
		this.idproducttype = idproducttype;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Category(long idcategory, long idproducttype, String categoryname, Set<Product> product) {
		super();
		this.idcategory = idcategory;
		this.idproducttype = idproducttype;
		this.categoryname = categoryname;
		this.product = product;
	}
	

	
	
	
}
