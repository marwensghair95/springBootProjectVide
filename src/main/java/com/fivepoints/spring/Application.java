package com.fivepoints.spring;

import com.fivepoints.spring.entities.Post;
import com.fivepoints.spring.entities.Role;
import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.entities.UserDetails;
import com.fivepoints.spring.repositories.PostRepository;
import com.fivepoints.spring.repositories.RoleRepository;
import com.fivepoints.spring.repositories.UserDetailsRepository;
import com.fivepoints.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
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
		// Clean up database tables
		this.roleRepository.deleteAllInBatch();
		this.userRepository.deleteAllInBatch();
		this.userDetailsRepository.deleteAllInBatch();
		this.postRepository.deleteAllInBatch();

		// Save roles
		Role superAdminRole = this.roleRepository.save(new Role("super-admin"));
		Role adminRole = this.roleRepository.save(new Role("admin"));
		Role userRole = this.roleRepository.save(new Role("user"));
		Role guestRole = this.roleRepository.save(new Role("guest"));


		// Save users
		User user1 = new User("hatem", "dagbouj",
					"hatem.dagbouj@fivepoints.fr", "123456789");

		// Save users details
		UserDetails userDetails1 = new UserDetails(20, new Date("11/11/1994"),
				"github.com/hatem", "linkedin.com/hatem");

		// Affect user1 to userDetails1
		user1.setDetails(userDetails1); // Set child reference
		userDetails1.setUser(user1); // Set parent reference
		this.userRepository.save(user1);


		// Save Posts
		Post post1 = new Post("","", true);
		Post post2 = new Post("","", true);
		Post post3 = new Post("","", true);
		// associate user1 to posts
		post1.setUser(user1);
		post2.setUser(user1);
		post3.setUser(user1);
		this.postRepository.save(post1);
		this.postRepository.save(post2);
		this.postRepository.save(post3);
		this.userRepository.save(user1);

		// ManyToMany Relations
		Set<Role> roles = new HashSet<>();
		roles.add(superAdminRole);
		roles.add(adminRole);
		roles.add(userRole);
		roles.add(guestRole);
		user1.setRoles(roles);
		this.userRepository.save(user1);


	}
}
