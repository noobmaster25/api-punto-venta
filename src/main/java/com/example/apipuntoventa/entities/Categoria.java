package com.example.apipuntoventa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id")
	private Integer id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String descripcion;

	public Categoria(@NotNull String nombre, @NotNull String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	
}
