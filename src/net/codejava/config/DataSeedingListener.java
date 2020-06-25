package net.codejava.config;


import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
 
import net.codejava.model.Roles;
import net.codejava.model.Users;
import net.codejava.Repository.RoleRepository;
import net.codejava.Repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//role
		if (roleRepository.findByrolename("ROLE_ADMIN") == null) {
			roleRepository.save(new Roles("ROLE_ADMIN"));
		}
		
		if (roleRepository.findByrolename("ROLE_MEMBER") == null) {
			roleRepository.save(new Roles("ROLE_MEMBER"));
		}
		
		// Admin account
		if (userRepository.findByusername("admin@gmail.com") == null) {
			Users admin = new Users();
			admin.setUsername("admin@gmail.com");
			admin.setPass_word("123456");
			admin.setFullname("Trần Ngọc Dũng");
			HashSet<Roles> roles = new HashSet<>();
			roles.add(roleRepository.findByrolename("ROLE_ADMIN"));
			roles.add(roleRepository.findByrolename("ROLE_MEMBER"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		
		
		// Member account
		if (userRepository.findByusername("member@gmail.com") == null) {
			Users user = new Users();
			user.setUsername("member@gmail.com");
			user.setPass_word("123456");
			user.setFullname("Mai Thúy Hằng");
			HashSet<Roles> roles = new HashSet<>();
			roles.add(roleRepository.findByrolename("ROLE_MEMBER"));
			user.setRoles(roles);
			userRepository.save(user);
		}
	}
}
