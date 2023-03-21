package com.example.apipuntoventa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detalle_orden_id")
	private Integer id;
	
	private String nombre;
	
	private Double precio;
	
	private Integer cantidad;
	
	private Double total;
	
	
	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Orden orden;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	public DetalleOrden(String nombre, Double precio, Integer cantidad, Double total, Orden orden, Producto producto) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.total = total;
		this.orden = orden;
		this.producto = producto;
	}


	
	
}
