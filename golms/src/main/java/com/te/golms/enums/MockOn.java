package com.te.golms.enums;

public enum MockOn {

	MOCK1("MOCK1"), MOCK2("MOCK2"), MOCK3("MOCK3"), MOCK4("MOCK4"), MOCK5("MOCK5");

	private final String mockOnType;

	MockOn(String mockOnType) {
		this.mockOnType = mockOnType;
	}

	public String getMockOn() {
		return mockOnType;
	}
}
