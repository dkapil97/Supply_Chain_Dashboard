package com.kd.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private final Key key;
	
	private final long expiration;

	public JwtUtil(@Value("${security.jwt.secret}") String secret,
			@Value("${security.jwt.expiration}")long expiration) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.expiration = expiration;
	}
	  public String generateToken(String username, String role) {
	        Date now = new Date();
	        Date expiry = new Date(now.getTime() + expiration);
	        return Jwts.builder()
	                .setSubject(username)
	                .claim("role", role)
	                .setIssuedAt(now)
	                .setExpiration(expiry)
	                .signWith(key)
	                .compact();
	    }
	  public Jws<Claims> validateToken(String token) {
	        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	    }

	    public String getUsername(String token) {
	        return validateToken(token).getBody().getSubject();
	    }

	    public String getRole(String token) {
	        return (String) validateToken(token).getBody().get("role");
	    }
}
