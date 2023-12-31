package com.te.spring.boot.auth;

import java.util.Optional;

public interface ApplicationUserDAO {
	Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
