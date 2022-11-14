package com.te.golms.enums;

public enum SkillRating {
	BELOW_AVERAGE("BELOW_AVERAGE"), AVERAGE("AVERAGE"), ABOVE_AVERAGE("ABOVE_AVERAGE"), GOOD("GOOD"),
	EXCELENT("EXCELENT");

	private final String grade;

	SkillRating(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}
}
