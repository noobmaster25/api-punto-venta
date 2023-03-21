package com.example.apipuntoventa.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String secretKey;
	
	@Value("${app.jwt-expiration-miliseconds}")
	private Integer expirationInMiliseconds;
	
	public String crearToken(Authentication authentication) {
		
		String username = authentication.getName();
		
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + expirationInMiliseconds);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(fechaActual)
				.setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
		return token;
	}
	
	public String obtenerUsernamePorToken(String token) {
		Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		return claim.getSubject();
	}
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty");
		}
		return false;
	}
	
}
