package com.example.apipuntoventa.dto;

import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaNuevaDTO {

	@NotEmpty
	private String nombre;	
	@NotEmpty
	private String descripcion;

}
