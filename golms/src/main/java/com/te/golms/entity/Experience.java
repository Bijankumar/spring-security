package com.te.golms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.golms.enums.YearsOfExperience;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_experience_info")
public class Experience {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experience_id")
	private Integer experienceId;

	@NotNull(message = "NULL data passed for companyName")
	@NotBlank(message = "BLANK data passes companyName")
	@Column(name = "company_name")
	private String companyName;

	@NotNull(message = "NULL data passed for yearsOfExperience")
	@Enumerated(EnumType.ORDINAL)
	private YearsOfExperience yearsOfExperience;

	@NotNull(message = "NULL data passed for dataOfJoining")
	@Column(name = "data_of_joining")
	private LocalDate dataOfJoining;

	@NotNull(message = "NULL data passed for dataOfRelieving")
	@Column(name = "data_of_relieving")
	private LocalDate dataOfRelieving;

	@NotNull(message = "NULL data passed for designation")
	@NotBlank(message = "BLANK data passes designation")
	@Column(name = "designation")
	private String designation;

	@NotNull(message = "NULL data passed for location")
	@NotBlank(message = "BLANK data passes location")
	@Column(name = "location")
	private String location;
}
