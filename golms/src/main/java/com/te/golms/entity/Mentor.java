package com.te.golms.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor {
	@Id
	@Column(name = "mentor_id")
	private String mentorId;

	@NotNull(message = "NULL data passed for mentorName")
	@NotBlank(message = "BLANK data passes mentorName")
	@Size(max = 100)
	@Column(name = "mentor_name")
	private String mentorName;

	@NotNull(message = "NULL data passed for email")
	@NotBlank(message = "BLANK data passes for email")
	@Email(message = "Email is not valid")
	@NotEmpty(message = "Email cannot be empty")
	@Column(name = "mentor_email", unique = true)
	private String email;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Technology> technologies;

	@JsonIgnore
	@OneToOne(mappedBy = "batchMentor")
	private Batch traningBatche;
}
