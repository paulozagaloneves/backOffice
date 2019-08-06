package com.itsector.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackOfficeApplication {

	public static final int BCRYPT_STRENGTH = 5;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackOfficeApplication.class, args);
	}

}
