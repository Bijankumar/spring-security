package com.te.golms.enums;

public enum MockRating {
	BELOW_AVERAGE("BELOW_AVERAGE (50-59)"), AVERAGE("AVERAGE (60-69)"), ABOVE_AVERAGE("ABOVE_AVERAGE (70-79)"),
	GOOD("GOOD (80-89)"), EXCELENT("EXCELENT (90 Above)");

	private final String grade;

	MockRating(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

}
