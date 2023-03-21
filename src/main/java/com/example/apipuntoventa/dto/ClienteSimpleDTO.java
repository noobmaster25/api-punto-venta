package com.example.apipuntoventa.dto;

import com.example.apipuntoventa.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSimpleDTO {

	private Integer id;
	private String nombre;
	private String tipo;
	
	public ClienteSimpleDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nombre = cliente.getNombre();
		this.tipo = cliente.getTipo();
	}
}
