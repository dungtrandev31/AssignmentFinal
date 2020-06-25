package net.codejava.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Roles implements Serializable{


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Roles_id", nullable = false)
    private long id;
    
    @Column(name = "name", nullable = false)
    private String rolename;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    public Roles() {
	}
    
    public Roles(String rolename) {
		this.rolename = rolename;
	}
	
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", Rolename=" + rolename + ", users=" + users + "]";
	}

	public Roles(long id, String rolename, Set<Users> users) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.users = users;
	}
	



    
}
