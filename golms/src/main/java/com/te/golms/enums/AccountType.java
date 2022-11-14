package com.te.golms.enums;

public enum AccountType {
	EMPLOYEE("EMPLOYEE"), MENTOR("MENTOR"), ADMIN("ADMIN");

	private final String type;

	AccountType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
