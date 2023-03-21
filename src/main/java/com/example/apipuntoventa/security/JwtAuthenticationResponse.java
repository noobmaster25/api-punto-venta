package com.example.apipuntoventa.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {

	private String token;
	private String tipo = "Bearer";
	
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}
}
