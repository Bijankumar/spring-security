package com.te.golms.enums;

public enum MockType {

	MOCK("MOCK"), REMOCK("REMOCK");

	private final String mock;

	MockType(String mock) {
		this.mock = mock;
	}

	public String getMockType() {
		return mock;
	}
}
