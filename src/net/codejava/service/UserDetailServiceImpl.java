package net.codejava.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import net.codejava.model.Roles;
import net.codejava.model.Users;
import net.codejava.Repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	   @Autowired
	    private UserRepository userRepository;
	   
	   @Transactional
	   public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException{
		   Users user = userRepository.findByusername(Username);;
		   if (user == null) {
			throw new UsernameNotFoundException("Không tìm thấy người dùng");
		}
		   
		   Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		   Set<Roles> roles = user.getRoles();
		   for (Roles role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
			
		}
		   return new org.springframework.security.core.userdetails.User(user.getFullname(), user.getPass_word(), grantedAuthorities);
	   }
}
