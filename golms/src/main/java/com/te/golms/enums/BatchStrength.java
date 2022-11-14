package com.te.golms.enums;

public enum BatchStrength {
	INITIAL_STRENGTH("INITIAL_STRENGTH"), DROPOUT("DROPOUT"), TERMINATED("TERMINATED"), ABSCONDING("ABSCONDING"),
	PRESENT_STRENGTH("PRESENT_STRENGTH");

	private final String state;

	BatchStrength(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

}
