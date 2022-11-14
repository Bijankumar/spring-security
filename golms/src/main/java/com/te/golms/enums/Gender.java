package com.te.golms.enums;

public enum Gender {
	MALE("MALE"), FEMALE("FEMALE"), OTHER("OTHER");

	private final String genderType;

	Gender(String genderType) {
		this.genderType = genderType;
	}

	public String getGender() {
		return genderType;
	}
}
