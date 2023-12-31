package com.te.spring.boot.security.beans;

import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This is a bean class. 
 * In Spring Security we already have a class User. So, while importing it can be confusing and error prone.
 * */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	@ManyToMany(fetch = EAGER)
	private Collection<Role> roles = new ArrayList<>();
}
