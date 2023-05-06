package com.example.apipuntoventa.service;

import com.example.apipuntoventa.dto.LoginDTO;
import com.example.apipuntoventa.dto.RegistroUsuarioDTO;

public interface IAuthenticationService {
	
	String logeoPorUsuarioContrasenia(LoginDTO loginDto);

	void registroUsuario(RegistroUsuarioDTO registroUsuarioDto);

}
