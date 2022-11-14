package com.te.golms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.te.golms.enums.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_secondary_info")
public class SecondaryInfo {
	@Id
	@Column(name = "pan_no")
	private String panNo;

	@NotNull(message = "NULL data passed for aadhaarNo")
	@NotBlank(message = "BLANK data passes aadhaarNo")
	@Column(name = "aadhaar_no", unique = true)
	private String aadhaarNo;

	@NotNull(message = "NULL data passed for fatherName")
	@NotBlank(message = "BLANK data passes fatherName")
	@Column(name = "father_name")
	private String fatherName;

	@NotNull(message = "NULL data passed for motherName")
	@NotBlank(message = "BLANK data passes motherName")
	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "spouse_name")
	private String spouseName;

	@Nullable
	@Column(name = "passport_no", unique = true)
	private String passportNo;

	@NotNull(message = "NULL data passed for maritalStatus")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
}
