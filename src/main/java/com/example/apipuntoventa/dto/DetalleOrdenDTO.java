package com.example.apipuntoventa.dto;

import com.example.apipuntoventa.entities.DetalleOrden;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenDTO {

	private Integer id;

	private String nombre;
	private Integer cantidad;
	private Double total;
	private Integer ordenId;
	@JsonProperty("productos")
	private ProductoSimpleDTO productoDto;

	
	public DetalleOrdenDTO(DetalleOrden detalleOrden) {
		this.id = detalleOrden.getId();
		this.nombre = detalleOrden.getNombre();
		this.cantidad = detalleOrden.getCantidad();
		this.total = detalleOrden.getTotal();
		this.ordenId = detalleOrden.getOrden().getId();
		this.productoDto = new ProductoSimpleDTO(detalleOrden.getProducto());
	}
}
