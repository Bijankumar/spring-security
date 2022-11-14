package com.te.golms.enums;

public enum Techno {

	JFSR("JFSR"), JFSA("JFSA"), REACT("REACT"), ANGULAR("ANGULAR"), JAVA_SPRING_BOOT("JAVA_SPRING_BOOT"),
	NODE_EXPRESS("NODE_EXPRESS_JS");

	private final String technology;

	Techno(String technology) {
		this.technology = technology;
	}

	public String getTechnology() {
		return technology;
	}
}
