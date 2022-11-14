package com.te.spring.boot.security;

public enum ApplicationUserPermissions {
	/*
	 * Here we have created certain group permissions. These permissions can be
	 * assigned to the roles respectively as required.
	 */
	STUDENT_READ("student: read"), STUDENT_WRITE("student: write"), COURSE_READ("course: read"),
	COURSE_WRITE("course: write");

	private final String permission;

	ApplicationUserPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
