package com.te.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAApplication.class, args);
		System.out.println("Spring Boot Application Running!");
	}

}
