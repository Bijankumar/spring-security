package com.te.golms.enums;

public enum MaritalStatus {
	MARRIED("MARRIED"), WIDOWED("WIDOWED"), SEPERATED("SEPERATED"), DIVORSED("DIVORSED"), SINGLE("SINGLE");

	private final String maritalStatusType;

	MaritalStatus(String maritalStatusType) {
		this.maritalStatusType = maritalStatusType;
	}

	public String getMaritalStatus() {
		return maritalStatusType;
	}
}
