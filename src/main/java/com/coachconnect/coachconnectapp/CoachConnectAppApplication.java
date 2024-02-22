package com.coachconnect.coachconnectapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coachconnect.coachconnectapp.model.EnumRole;
import com.coachconnect.coachconnectapp.model.Role;
import com.coachconnect.coachconnectapp.model.RoleRepository;


@SpringBootApplication
public class CoachConnectAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachConnectAppApplication.class, args);
	}
/*
	@Bean
	ApplicationRunner init(RoleRepository roleRepo) {
		return args -> {
			Role admin = new Role(EnumRole.ROLE_ADMIN);
			Role client = new Role(EnumRole.ROLE_CLIENT);
			Role instructor = new Role(EnumRole.ROLE_INSTRUCTOR);
			roleRepo.save(admin);
			roleRepo.save(client);
			roleRepo.save(instructor);

		};
	}*/
}
