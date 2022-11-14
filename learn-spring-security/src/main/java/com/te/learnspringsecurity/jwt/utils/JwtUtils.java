package com.te.learnspringsecurity.jwt.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

	private String secret = "some secret key";

	/* 1. Method for token generation! */
	public String generateToken(String subject) {
		log.error("Generationg token.");
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(subject);
		builder.setIssuer("VIJENDRA SINGH");
		builder.setIssuedAt(new Date(System.currentTimeMillis()));
		builder.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)));
		builder.signWith(SignatureAlgorithm.HS256, secret.getBytes());
		log.error("Token generated.");
		return builder.compact();
	}

	/* 2. Method for reading claims! */
	public Claims getClaims(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(secret.getBytes());
		Jws<Claims> parseClaimsJws = parser.parseClaimsJws(token);
		log.error("In getClaims(String token) method to grt payload(claims) from the jwt token.");
		return parseClaimsJws.getBody();
	}

	/* 3. Method to get expiration date! */
	public Date getExpirationDate(String token) {
		log.error("In getExpirationDate(String token) method to get expiration date of token.");
		return getClaims(token).getExpiration();
	}

	/* 4. Method to get subject user name! */
	public String getUsername(String token) {
		log.error("In getUsername(String token) method to get the username from the payload(claims).");
		return getClaims(token).getSubject();
	}

	/* 5. Check if the token is expired! */
	public boolean isTokenExpired(String token) {
		log.error("In isTokenExpired(String token) method to check if the token is expired or not.");
		Date expirationDate = getExpirationDate(token);
		return expirationDate.before(new Date(System.currentTimeMillis()));
	}

	/* 6. Method to validate a token! */
	public boolean validateToken(String token, String username) {
		log.error(
				"In validateToken(String token, String username) to validate token by extractiong the username from the token and checking with the passed username.");
		String usernameFromToken = getUsername(token);
		return username.equals(usernameFromToken) && !isTokenExpired(token);
	}
}
