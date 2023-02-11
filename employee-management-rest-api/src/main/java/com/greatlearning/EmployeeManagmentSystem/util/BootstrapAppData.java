package com.greatlearning.EmployeeManagmentSystem.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.EmployeeManagmentSystem.entity.Role;
import com.greatlearning.EmployeeManagmentSystem.entity.User;
import com.greatlearning.EmployeeManagmentSystem.repository.UserRepository;

@Configuration
public class BootstrapAppData {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public BootstrapAppData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertAppData(ApplicationReadyEvent event) {
		System.out.println("Application is ready :: ");
		System.out.println("Inserting the test data :: ");
		User superuser = new User("superuser", this.passwordEncoder.encode("welcome"));

		Role userRole1 = new Role("ADMIN");
		Role userRole2 = new Role("USER");

		superuser.addRole(userRole1);
		superuser.addRole(userRole2);

		this.userRepository.save(superuser);

	}

}
