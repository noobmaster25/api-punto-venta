package com.example.apipuntoventa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipuntoventa.dto.LoginDTO;
import com.example.apipuntoventa.dto.RegistroUsuarioDTO;
import com.example.apipuntoventa.security.JwtAuthenticationResponse;
import com.example.apipuntoventa.service.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/punto-venta-api/v0/auth")
public class AuthController {

	@Autowired
	private AuthenticationServiceImpl authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDto) throws InterruptedException {
		String token = authenticationService.logeoPorUsuarioContrasenia(loginDto);
		JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse(token);

		return ResponseEntity.ok().header("Authentication", "Bearer " + token).body(jwtResponse);
	}

	@PostMapping("/registro")
	public ResponseEntity<?> registro(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDto) {
		authenticationService.registroUsuario(registroUsuarioDto);
		return ResponseEntity.ok("usuario registrado");
	}

}
