package com.example.apipuntoventa.dto;



import com.example.apipuntoventa.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Integer id;
	private String nombre;
	private String telefono;	
	private String tipo;
	private String correo;
	private String direccion;
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nombre = cliente.getNombre();
		this.telefono = cliente.getTelefono();
		this.tipo = cliente.getTipo();
		this.correo = cliente.getCorreo();
		this.direccion = cliente.getDireccion();
	}
}
