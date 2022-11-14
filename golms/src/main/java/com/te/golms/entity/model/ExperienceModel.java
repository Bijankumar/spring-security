package com.te.golms.entity.model;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.YearsOfExperience;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceModel {
	private String companyName;
	@Enumerated(EnumType.ORDINAL)
	private YearsOfExperience yearsOfExperience;
	private LocalDate dataOfJoining;
	private LocalDate dataOfRelieving;
	private String designation;
	private String location;
}
