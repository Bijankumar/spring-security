package com.te.golms.entity;

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
import com.te.golms.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_education_details_info")
public class EducationDetails {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "education_id")
	private Integer educationId;

	@NotNull(message = "NULL data passed for educationType")
	@NotBlank(message = "BLANK data passes educationType")
	@Column(name = "education_type")
	private String educationType;

	@NotNull(message = "NULL data passed for yearOfPassing")
	@Column(name = "year_of_passing")
	private Integer yearOfPassing;

	@NotNull(message = "NULL data passed for percentage")
	@Column(name = "percentage")
	private Double percentage;

	@NotNull(message = "NULL data passed for universityName")
	@NotBlank(message = "BLANK data passes universityName")
	@Column(name = "university_name")
	private String universityName;

	@NotNull(message = "NULL data passed for instituteName")
	@NotBlank(message = "BLANK data passes instituteName")
	@Column(name = "institute_name")
	private String instituteName;

	@NotNull(message = "NULL data passed for specialization")
	@NotBlank(message = "BLANK data passes specialization")
	@Column(name = "specialization")
	private String specialization;

	@Enumerated(EnumType.STRING)
	private State state;
}
