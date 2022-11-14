package com.te.golms.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GoLmsUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer goLmsUserId;
	@Column(unique = true)
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "goLmsUserId"))
	@Column(name = "role")
	private Set<String> roles;
	private boolean isPasswordReset = false;
	private boolean isAccountActive = false;

}
