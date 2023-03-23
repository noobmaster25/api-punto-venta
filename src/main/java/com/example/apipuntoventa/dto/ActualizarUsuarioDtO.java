package com.example.apipuntoventa.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarUsuarioDtO {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String username;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;
	
	@NotEmpty
	private List<RolNuevoDTO> roles;
}
