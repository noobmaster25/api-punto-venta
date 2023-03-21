package com.example.apipuntoventa.dto;


import com.example.apipuntoventa.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

	private Integer id;
	private String nombre;	
	private String descripcion;
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nombre = categoria.getNombre();
		this.descripcion = categoria.getDescripcion();
	}
}
