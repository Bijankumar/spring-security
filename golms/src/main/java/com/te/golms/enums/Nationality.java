package com.te.golms.enums;

public enum Nationality {
	INDIA("INDIA"), CHINA("CHINA"), PAKISTAN("PAKISTAN"), SHRI_LANKA("SHRI_LANKA");

	private final String nation;

	Nationality(String nation) {
		this.nation = nation;
	}

	public String getNationality() {
		return nation;
	}
}
