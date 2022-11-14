package com.te.golms.enums;

public enum BatchStatus {

	IN_PROGRESS("IN_PROGRESS"), COMPLETED("COMPLETED"), TO_BE_STARTED("TO_BE_STARTED");

	private final String status;

	BatchStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
