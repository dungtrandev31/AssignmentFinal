package net.codejava.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcustomer")
	private long idcustomer;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "addresscustomer")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Order_Customer", joinColumns = @JoinColumn(name = "idcustomer"),
			inverseJoinColumns = @JoinColumn(name = "idOrderInfo"))
	private Collection<OrderInfo> orderInfos;
	

	public long getIdcustomer() {
		return idcustomer;
	}

	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Customer(long idcustomer, String name, String address, String email, String phone) {
		super();
		this.idcustomer = idcustomer;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "Customer [idcustomer=" + idcustomer + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", orderInfos=" + orderInfos + "]";
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(Collection<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}



	
	
}
