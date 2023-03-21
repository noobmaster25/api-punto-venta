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
public class DetalleOrdenNuevaDTO {
	
	@NotEmpty
	private String nombre;
	
	@PositiveOrZero
	private Integer cantidad;
	
	@Positive
	private Double precio;
	
	@Positive
	private Double total;
	
	@NotNull
	@Positive
	private Integer OrdenId;
	@NotNull
	@Positive
	private Integer productoId;
}
