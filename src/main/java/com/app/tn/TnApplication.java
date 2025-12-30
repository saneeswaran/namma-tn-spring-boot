package com.app.tn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TnApplication {

	public static void main(String[] args) {
		SpringApplication.run(TnApplication.class, args);
	}

}
