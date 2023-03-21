package com.example.apipuntoventa.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes",uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Integer id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String telefono;
	
	private String tipo;
	
	@NotNull
	private String correo;
	
	private String direccion;
	

	public Cliente(@NotNull String nombre, @NotNull String telefono, String tipo, @NotNull String correo,
			String direccion) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.tipo = tipo;
		this.correo = correo;
		this.direccion = direccion;
	}

	
	
	
}
