package com.te.spring.boot.service.implementation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.spring.boot.beans.CustomUser;
import com.te.spring.boot.custom.exception.UserNotFoundException;
import com.te.spring.boot.repository.CustomUserRepository;
import com.te.spring.boot.service.CustomUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImplementation implements CustomUserService, UserDetailsService {

	private final CustomUserRepository customUserRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public String saveUser(CustomUser customUser) {
		customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
		return customUserRepository.save(customUser).getUsername();
	}

	@Override
	public Optional<CustomUser> findByUsername(String username) {
		return customUserRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomUser> user = findByUsername(username);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User does not exist in the database!");
		}
		CustomUser customUser = user.get();
		return new User(customUser.getUsername(), customUser.getPassword(), customUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
	}

}
