package com.te.golms.enums;

public enum AddressType {
	PERMANENT("PERMANENT"), TEMPORARY("TEMPORARY");

	private final String type;

	AddressType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
