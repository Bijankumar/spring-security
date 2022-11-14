package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.te.golms.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@Column(name = "admin_id")
	private String adminId;

	@NotNull
	@NotBlank
	@Column(name = "admin_name")
	private String name;

	@NotNull
	@NotBlank
	@Email(message = "Email is not valid")
	@Column(name = "admin_email", unique = true)
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

}