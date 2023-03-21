package com.example.apipuntoventa.dto;

import com.example.apipuntoventa.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSimpleDTO {

	
	private Integer id;
	private String nombre;
	private String descriptcion;
	private String categoria;
	
	public ProductoSimpleDTO(Producto producto) {
		this.id = producto.getId();
		this.nombre = producto.getNombre();
		this.descriptcion = producto.getDescripcion();
		this.categoria = producto.getCategoria().getNombre();
	}
}
