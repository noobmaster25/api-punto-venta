package com.example.apipuntoventa.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.entities.Rol;
import com.example.apipuntoventa.entities.Usuario;
import com.example.apipuntoventa.repository.IUsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private IUsuarioRepository usuarioRepo;
	
	public UserDetailServiceImpl(IUsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado"));
		return new User(usuario.getUserName(), usuario.getContrasenia(), obtenerRoles(usuario.getRoles()));
	}
	
	private List<GrantedAuthority> obtenerRoles(List<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
	

}
