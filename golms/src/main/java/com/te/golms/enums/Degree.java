package com.te.golms.enums;

public enum Degree {

	BE_CSE("BE_CSE"), BE_NON_CSE("BE_NON_CSE"), PG("PG"), PHD("PHD");

	private final String degreeType;

	Degree(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getDegree() {
		return degreeType;
	}

}
