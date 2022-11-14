package com.te.golms.entity.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.golms.enums.BloodGroup;
import com.te.golms.enums.Degree;
import com.te.golms.enums.EmployeeStatus;
import com.te.golms.enums.Gender;
import com.te.golms.enums.Nationality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
	private String empId;
	private String empName;
	private String email;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoining;
	private String designation;
	@Enumerated(EnumType.STRING)
	private Nationality nationality;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;
	@Enumerated(EnumType.STRING)
	private Degree degree;
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
	private SecondaryInfoModel secondaryInfo;
	private List<EducationDetailsModel> educationDetails = new ArrayList<>();
	private List<AddressModel> address;
	private BankDetailsModel bankDetails;
	private Set<TechnicalSkillModel> technicalSkill = new HashSet<>();
	private List<ExperienceModel> experience = new ArrayList<>();
	private List<ContactModel> contacts = new ArrayList<>();
}
