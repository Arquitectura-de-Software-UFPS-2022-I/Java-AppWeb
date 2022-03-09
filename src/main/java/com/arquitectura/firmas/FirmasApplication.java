package com.arquitectura.firmas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import models.FileDto;
import models.UserDto;
import services.impl.ApiService;

@SpringBootApplication
public class FirmasApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FirmasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiService api = new ApiService();

		try {
			for (UserDto user : api.getUserService().getUser()) {
				System.out.println(user);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
//		String password = "12345";
//		for(int i=0; i<2; i++) {
//			String bcryptPassword= passwordEncoder.encode(password);
//			System.out.println(bcryptPassword);
//		}
	}

}
