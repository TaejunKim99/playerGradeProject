package com.example.soleproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoleprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoleprojectApplication.class, args);
	}

}
