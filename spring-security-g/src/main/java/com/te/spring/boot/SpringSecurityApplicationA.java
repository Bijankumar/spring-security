package com.te.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplicationA {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplicationA.class, args);
		System.out.println("Spring Boot Application Started!");
		System.out.println("It has DB authentication!");
	}

}
