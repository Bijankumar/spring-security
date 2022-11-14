package com.te.golms.enums;

public enum EmployeeStatus {

	ABSCONDED("ABSCONDED"), TERMINATED("TERMINATED"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String status;

	EmployeeStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
