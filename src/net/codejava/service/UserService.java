package net.codejava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.Repository.UserRepository;
import net.codejava.model.Users;

@Service
@Transactional
public class UserService {
	
	@Autowired UserRepository userRepository;
	
	public void save(Users user) {
		userRepository.save(user);
	}
	
	public void saveUser(String Pass_word,String address,String email,String fullname,String phone,String username,String Users_id) {
		userRepository.saveInfoUser(Pass_word, address, email, fullname, phone, username, Long.parseLong(Users_id));
	}
	
	public List<String> getRole(long id) {
		return userRepository.getRole(id);
	}
	
	public List<Users> listAll() {
		return (List<Users>) userRepository.findAll();
	}
	
	public Users get(Long id) {
		return userRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public List<Users> listUser(String username){
		return userRepository.getUser(username);
		
	}
}
