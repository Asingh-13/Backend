package com.indigo.indigosan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.indigo.indigosan.model")
@EnableJpaRepositories(basePackages = "com.indigo.indigosan.repo")
public class IndigosanApplication {
	public static void main(String[] args) {
		SpringApplication.run(IndigosanApplication.class, args);
	}

}
