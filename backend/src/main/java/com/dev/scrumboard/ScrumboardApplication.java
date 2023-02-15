package com.dev.scrumboard;

import com.dev.scrumboard.models.User;
import com.dev.scrumboard.repositories.UserRepository;
import com.dev.scrumboard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrumBoardApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ScrumBoardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User();
		u1.setName("Tommy Shelby");
		u1.setUserName("shelby");
		u1.setEmail("shelby@email.com");


		User u2 = new User();
		u2.setName("Luis Silva");
		u2.setUserName("luisito");
		u2.setEmail("luisito@email.com");


		User u3 = new User();
		u3.setName("Arthur Shelby");
		u3.setUserName("arthurshelby");
		u3.setEmail("chefao@email.com");


		User u4 = new User();
		u4.setName("Joao Luis");
		u4.setUserName("jao");
		u4.setEmail("jotinha@email.com");


		User u5 = new User();
		u5.setName("John Shelby");
		u5.setUserName("joaoshelby");
		u5.setEmail("joao@email.com");

		this.userService.create(u1);
		this.userService.create(u2);
		this.userService.create(u3);
		this.userService.create(u4);
		this.userService.create(u5);


	}
}
