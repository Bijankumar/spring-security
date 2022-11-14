package com.te.golms.enums;

public enum YearsOfExperience {
	FRESHER(0), ONE(1), TWO(2), THREE(3), FOUR(4);

	private final Integer experience;

	YearsOfExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getYearsOfExperience() {
		return experience;
	}

}
