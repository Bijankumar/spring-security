package com.te.spring.boot.security;

import java.util.HashSet;
import java.util.Set;

class PermissionSet {
	static Set<ApplicationUserPermissions> studentPermissionSet = new HashSet<>();
	static Set<ApplicationUserPermissions> adminPermissionSet = new HashSet<>();
	static {
		adminPermissionSet.add(ApplicationUserPermissions.STUDENT_READ);
		adminPermissionSet.add(ApplicationUserPermissions.STUDENT_WRITE);
		adminPermissionSet.add(ApplicationUserPermissions.COURSE_READ);
		adminPermissionSet.add(ApplicationUserPermissions.COURSE_WRITE);
	}
}

public enum ApplicationUserRoles {
	/*
	 * Here we created two types of roles and each roles has certain set of
	 * permissions.
	 */
	STUDENT(PermissionSet.studentPermissionSet), ADMIN(PermissionSet.adminPermissionSet);

	private final Set<ApplicationUserPermissions> permissionSet;

	ApplicationUserRoles(Set<ApplicationUserPermissions> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public Set<ApplicationUserPermissions> getPermissionSet() {
		return permissionSet;
	}
}
