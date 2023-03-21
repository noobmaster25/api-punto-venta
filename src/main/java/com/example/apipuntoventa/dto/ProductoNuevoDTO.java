package com.example.apipuntoventa.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoNuevoDTO {
	
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String descripcion;
	@PositiveOrZero
	private Integer cantidad;
	@Positive
	private Double precio;
	
	@Positive
	@NotNull
	private Integer categoriaId;
}
