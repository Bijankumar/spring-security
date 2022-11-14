package com.te.golms.enums;

public enum ContactType {

	PRIMARY("PRIMARY"), EMERGENCY("EMERGENCY"), WHATSAPP("WHATSAPP");

	private final String type;

	ContactType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
