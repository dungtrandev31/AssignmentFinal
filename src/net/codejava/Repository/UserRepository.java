package net.codejava.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.model.Users;

public interface UserRepository extends CrudRepository<Users, Long>{
	Users findByusername(String userName);
	
	
	@Query( value ="FROM Users where username = :username")
	public List<Users> getUser(@Param("username") String username);
	
	
	@Transactional
	@Modifying
	@Query( value ="UPDATE Users c  SET c.Pass_word = :Pass_word,  c.address = :address, c.email = :email, c.fullname = :fullname, c.phone = :phone, c.username= :username WHERE c.id  = :Users_id")
	void saveInfoUser(@Param("Pass_word") String Pass_word,@Param("address") String address,@Param("email") String email,@Param("fullname") String fullname,@Param("phone") String phone,@Param("username") String username,@Param("Users_id") long id);
	
	
	@Query( value ="select r.rolename FROM Roles r  join r.users u where u.id = :id")
	public List<String> getRole(@Param("id") long id);
}
