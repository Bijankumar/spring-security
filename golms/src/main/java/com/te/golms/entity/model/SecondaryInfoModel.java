package com.te.golms.entity.model;

import com.te.golms.enums.MaritalStatus;

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
public class SecondaryInfoModel {
	private String panNo;
	private String aadhaarNo;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String passportNo;
	private MaritalStatus maritalStatus;
}
