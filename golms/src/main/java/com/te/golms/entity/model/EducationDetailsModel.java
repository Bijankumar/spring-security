package com.te.golms.entity.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDetailsModel {
	private String educationType;
	private Integer yearOfPassing;
	private Double percentage;
	private String universityName;
	private String instituteName;
	private String specialization;
	@Enumerated(EnumType.STRING)
	private State state;
}
