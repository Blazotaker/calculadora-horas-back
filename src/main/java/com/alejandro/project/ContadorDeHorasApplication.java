package com.alejandro.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;


@SpringBootApplication()
public class ContadorDeHorasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContadorDeHorasApplication.class, args);
	}

}
