package com.example.apipuntoventa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.LoginDTO;
import com.example.apipuntoventa.dto.RegistroUsuarioDTO;
import com.example.apipuntoventa.entities.Rol;
import com.example.apipuntoventa.entities.Usuario;

import com.example.apipuntoventa.exceptions.ConflictException;
import com.example.apipuntoventa.repository.IRolRepository;
import com.example.apipuntoventa.repository.IUsuarioRepository;
import com.example.apipuntoventa.security.JwtTokenProvider;
import com.example.apipuntoventa.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Autowired
	private IRolRepository rolRepo;

	@Override
	public String logeoPorUsuarioContrasenia(LoginDTO loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.crearToken(authentication);
		return token;

	}

	@Override
	public void registroUsuario(RegistroUsuarioDTO registroUsuarioDto) {
		Optional<Usuario> usuarioPorUsername = usuarioRepo.findByUserName(registroUsuarioDto.getUsername());
		Optional<Usuario> usuarioPorCorreo = usuarioRepo.findByEmail(registroUsuarioDto.getEmail());

		if (usuarioPorUsername.isPresent())
			throw new ConflictException("usuario con username " + registroUsuarioDto.getUsername() + " ya existe");
		if (usuarioPorCorreo.isPresent())
			throw new ConflictException("usuario con correo " + registroUsuarioDto.getEmail() + " ya existe");

		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setNombre(registroUsuarioDto.getNombre());
		usuarioNuevo.setEmail(registroUsuarioDto.getEmail());
		usuarioNuevo.setApellido(registroUsuarioDto.getApellido());
		usuarioNuevo.setUserName(registroUsuarioDto.getUsername());
		usuarioNuevo.setContrasenia(passwordEncoder.encode(registroUsuarioDto.getPassword()));

		List<Rol> roles = new ArrayList<>();
		roles.add(rolRepo.findByNombre("ROLE_USER").get());

		usuarioNuevo.setRoles(roles);

		usuarioRepo.save(usuarioNuevo);

	}
}
