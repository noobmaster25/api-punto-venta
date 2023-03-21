package com.example.apipuntoventa.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNuevoDTO {
	
	@NotEmpty
	private String nombre;
	
	private String telefono;
	@NotEmpty
	@Email
	private String correo;
	@NotEmpty
	private String direccion;
	@NotEmpty
	private String tipo;
	

	
}
