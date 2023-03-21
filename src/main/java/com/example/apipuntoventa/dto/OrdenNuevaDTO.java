package com.example.apipuntoventa.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenNuevaDTO {

	@Positive
	private Integer numeroOrden;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
	@NotNull
	@Positive
	private Integer clienteId;
	
	@NotEmpty
	@JsonProperty("detallesOrden")
	private List<DetalleOrdenNuevaDTO> detallesOrdenNuevaDto;
	
	
}
