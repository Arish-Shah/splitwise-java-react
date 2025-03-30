package com.assignment.splitwise.util;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${security.jwt.secret}")
	private String secret;

	@Value("${security.jwt.expiration-time}")
	private Long expirationTime;

	public String generateToken(UUID userId) {
		return Jwts
			.builder()
			.subject(userId.toString())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + expirationTime))
			.signWith(this.getSecretKey())
			.compact();
	}

	public UUID getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(this.getSecretKey()).build().parseSignedClaims(token).getPayload();
		String sub = claims.getSubject();
		return UUID.fromString(sub);
	}

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret));
	}

}
