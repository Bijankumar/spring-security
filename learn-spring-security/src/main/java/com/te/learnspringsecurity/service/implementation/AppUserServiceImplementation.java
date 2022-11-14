package com.te.learnspringsecurity.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.learnspringsecurity.entity.AppUser;
import com.te.learnspringsecurity.repository.AppUserRepository;
import com.te.learnspringsecurity.service.AppUserService;

import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {

	private final AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("In loadUserByUsername(String username) method.");
		// 1. Find AppUser from database using username.
		// 2. If AppUser is present, convert AppUser object into spring security User
		// object (Needs GrantedAuthorities object) and return User object.
		// 3. Or else throw new UsernameNotFoundException(...).
		Optional<AppUser> optional = appUserRepository.findByUsername(username);
		log.debug("Find AppUser from database using username.");
		if (optional.isPresent()) {
			log.debug(
					"If AppUser is present, convert AppUser object into spring security User object (Needs GrantedAuthorities object) and return User object.");
			AppUser appUser = optional.get();
			List<SimpleGrantedAuthority> authorities = appUser.getRoles().stream()
					.map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
			return new User(appUser.getUsername(), appUser.getPassword(), authorities);
		}
		log.debug("Or else throw new UsernameNotFoundException(...).");
		throw new UsernameNotFoundException("User with the given username " + username + " does not exist!");
	}

}
