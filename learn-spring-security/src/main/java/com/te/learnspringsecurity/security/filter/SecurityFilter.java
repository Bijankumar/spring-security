package com.te.learnspringsecurity.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.learnspringsecurity.jwt.utils.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.error("In doFilterInternal(...) method.");
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			log.error("Token is present and starts with 'Bearer '.");
			String token = bearerToken.substring(7);
			String username = jwtUtils.getUsername(token);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				log.error("Username not null and security context is empty.");
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				boolean validateToken = jwtUtils.validateToken(token, userDetails.getUsername());
				if (validateToken) {
					log.error("Token validated.");
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), 
							userDetails.getPassword(), 
							userDetails.getAuthorities()
							);
					
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					log.error("Security context is set.");
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
