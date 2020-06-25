package net.codejava.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Users_id", nullable = false)
	private long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "Pass_word", nullable = false)
	private String Pass_word;
	@Column(name = "full_Name", nullable = false)
	private String fullname;

	@ManyToMany
	@JoinTable(name = "Users_Role", joinColumns = @JoinColumn(name = "Users_id"), inverseJoinColumns = @JoinColumn(name = "Roles_id"))
	private Set<Roles> roles;
	
	@Column(name = "email", nullable = false)
	private String  email;
	
	@Column(name = "sdt", nullable = false)
	private String phone;
	
	@Column(name = "address", nullable = false)
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass_word() {
		return Pass_word;
	}

	public void setPass_word(String pass_word) {
		Pass_word = pass_word;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Users(long id, String username, String pass_word, String fullname, Set<Roles> roles, String email,
			String phone, String address) {
		super();
		this.id = id;
		this.username = username;
		Pass_word = pass_word;
		this.fullname = fullname;
		this.roles = roles;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", Pass_word=" + Pass_word + ", fullname=" + fullname
				+ ", roles=" + roles + ", email=" + email + ", phone=" + phone + ", address=" + address + "]";
	}

}
