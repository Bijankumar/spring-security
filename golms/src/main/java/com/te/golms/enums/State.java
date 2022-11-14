package com.te.golms.enums;

public enum State {
	KARNATAKA("KARNATAKA"), KERALA("KERALA"), MADHYA_PRADESH("MADHYA_PRADESH");

	private final String fromState;

	State(String fromState) {
		this.fromState = fromState;
	}

	public String getState() {
		return fromState;
	}

}
