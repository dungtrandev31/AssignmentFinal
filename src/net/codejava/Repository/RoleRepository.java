package net.codejava.Repository;

import org.springframework.data.repository.CrudRepository;

import net.codejava.model.Roles;

public interface RoleRepository extends CrudRepository<Roles, Long>{
	Roles findByrolename(String Rolename);

}
