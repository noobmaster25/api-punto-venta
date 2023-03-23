package com.example.apipuntoventa.dto;

import java.util.List;

import com.example.apipuntoventa.entities.Rol;
import com.example.apipuntoventa.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String username;
	private List<Rol> roles;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getIdUsuario();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.email = usuario.getEmail();
		this.username = usuario.getUserName();
		this.roles = usuario.getRoles();
	}
}
