package com.fivepoints.spring;

import com.fivepoints.spring.entities.Role;
import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.repositories.PostRepository;
import com.fivepoints.spring.repositories.RoleRepository;
import com.fivepoints.spring.repositories.UserDetailsRepository;
import com.fivepoints.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Save roles
		Role superAdminRole = this.roleRepository.save(new Role("super-admin"));
		Role adminRole = this.roleRepository.save(new Role("admin"));
		Role userRole = this.roleRepository.save(new Role("user"));
		Role guestRole = this.roleRepository.save(new Role("guest"));

		// Save users
		User user1 = this.userRepository.save(new User("hatem", "dagbouj", "hatem.dagbouj@fivepoints.fr", "123456789"));

		// Save users details

		// Save Posts

	}
}
