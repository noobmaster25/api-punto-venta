package com.example.apipuntoventa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.ActualizarUsuarioDtO;
import com.example.apipuntoventa.dto.RolNuevoDTO;
import com.example.apipuntoventa.dto.UsuarioDTO;
import com.example.apipuntoventa.entities.Rol;
import com.example.apipuntoventa.entities.Usuario;
import com.example.apipuntoventa.exceptions.ConflictException;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.IRolRepository;
import com.example.apipuntoventa.repository.IUsuarioRepository;
import com.example.apipuntoventa.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private PasswordEncoder passwordEncoder;

	private IUsuarioRepository usuarioRepo;

	private IRolRepository rolRepo;

	
	public UsuarioServiceImpl(PasswordEncoder passwordEncoder, IUsuarioRepository usuarioRepo, IRolRepository rolRepo) {
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepo = usuarioRepo;
		this.rolRepo = rolRepo;
	}

	@Override
	public Page<UsuarioDTO> obtenerTodos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Usuario> usuarios = usuarioRepo.findAll(pageable);
		return usuarios.map(u -> new UsuarioDTO(u));
	}

	@Override
	public UsuarioDTO obtenerPorId(Integer id) {
		Usuario usuario = usuarioRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("usuario con id " + id + " no existe"));
		return new UsuarioDTO(usuario);
	}

	@Override
	public UsuarioDTO actualizarUsuario(Integer id, ActualizarUsuarioDtO actualizarUsuarioDto) {
		Usuario usuario = usuarioRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("usuario con id " + id + " no existe"));

		compararEmailYUsernameAnteriorConNuevo(usuario, actualizarUsuarioDto);

		usuario.setNombre(actualizarUsuarioDto.getNombre());
		usuario.setApellido(actualizarUsuarioDto.getApellido());
		usuario.setEmail(actualizarUsuarioDto.getEmail());
		usuario.setUserName(actualizarUsuarioDto.getUsername());
		usuario.setContrasenia(passwordEncoder.encode(actualizarUsuarioDto.getPassword()));

		List<Rol> roles = new ArrayList<>();

		for (RolNuevoDTO rol : actualizarUsuarioDto.getRoles()) {
			Rol rolNuevo = rolRepo.findByNombre(rol.getNombre())
					.orElseThrow(() -> new NotFoundException("rol" + rol.getNombre() + " no encontrado"));
			roles.add(rolNuevo);
		}
		usuario.setRoles(roles);
		return new UsuarioDTO(usuarioRepo.save(usuario));

	}

	@Override
	public void eliminarUsuario(Integer id) {
		usuarioRepo.findById(id).orElseThrow(() -> new NotFoundException("usuario con id " + id + " no existe"));
		usuarioRepo.deleteById(id);
	}

	private void compararEmailYUsernameAnteriorConNuevo(Usuario usuarioViejo,
			ActualizarUsuarioDtO actualizarUsuarioDto) {

		Optional<Usuario> usuarioPorUsername = usuarioRepo.findByUserName(actualizarUsuarioDto.getUsername());
		Optional<Usuario> usuarioPorEmail = usuarioRepo.findByEmail(actualizarUsuarioDto.getEmail());

		if (usuarioPorUsername.isPresent() && usuarioPorUsername.get().getIdUsuario() != usuarioViejo.getIdUsuario()) {
			throw new ConflictException("usuario con username " + actualizarUsuarioDto.getUsername() + " ya existe");
		}
		if (usuarioPorEmail.isPresent() && usuarioPorUsername.get().getIdUsuario() != usuarioViejo.getIdUsuario()) {
			throw new ConflictException("usuario con email " + actualizarUsuarioDto.getEmail() + " ya existe");
		}

	}

}
