package com.example.apipuntoventa.dto;



import com.example.apipuntoventa.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	private Double precio;
	private String categoria;
	
	public ProductoDTO(Producto producto) {
		this.id = producto.getId();
		this.nombre = producto.getNombre();
		this.descripcion = producto.getDescripcion();
		this.cantidad = producto.getCantidad();
		this.precio = producto.getPrecio();
		this.categoria = producto.getCategoria() != null ?producto.getCategoria().getNombre() : "sin categoira";		
	}
}
