package com.te.spring.boot.security;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

class PermissionSet {
	static Set<ApplicationUserPermissions> studentPermissionSet = new HashSet<>();
	static Set<ApplicationUserPermissions> adminPermissionSet = new HashSet<>();
	static Set<ApplicationUserPermissions> adminTraineePermissionSet = new HashSet<>();
	static {
		adminPermissionSet.add(ApplicationUserPermissions.STUDENT_READ);
		adminPermissionSet.add(ApplicationUserPermissions.STUDENT_WRITE);
		adminPermissionSet.add(ApplicationUserPermissions.COURSE_READ);
		adminPermissionSet.add(ApplicationUserPermissions.COURSE_WRITE);

		adminTraineePermissionSet.add(ApplicationUserPermissions.STUDENT_READ);
		adminTraineePermissionSet.add(ApplicationUserPermissions.COURSE_READ);
	}
}

public enum ApplicationUserRoles {
	/*
	 * Here we created two types of roles and each roles has certain set of
	 * permissions.
	 */
	STUDENT(PermissionSet.studentPermissionSet), ADMIN(PermissionSet.adminPermissionSet),
	ADMINTRAINEE(PermissionSet.adminTraineePermissionSet);

	private final Set<ApplicationUserPermissions> permissionSet;

	ApplicationUserRoles(Set<ApplicationUserPermissions> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public Set<ApplicationUserPermissions> getPermissionSet() {
		return permissionSet;
	}

	/*
	 * In order to have list of permissions a role has, we need to create the list
	 * of objects of SimpleGrantedAuthority.
	 */
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissionSet().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
