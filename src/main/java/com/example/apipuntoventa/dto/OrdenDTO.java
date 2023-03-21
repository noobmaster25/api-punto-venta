package com.example.apipuntoventa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.apipuntoventa.entities.DetalleOrden;
import com.example.apipuntoventa.entities.Orden;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDTO {

	private Integer id;
	@JsonProperty("numero_orden")
	private Integer numeroOrden;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonProperty("fecha_creacion")
	private LocalDateTime fechaCreacion;
	
	@JsonProperty("detalles")
	private List<DetalleOrdenDTO> detallesOrdenDto = new ArrayList<>();
	
	@JsonProperty("cliente")
	private ClienteSimpleDTO clienteDto;
	
	public OrdenDTO(Orden orden) {
		this.id = orden.getId();
		this.numeroOrden = orden.getNumeroOrden();
		this.fechaCreacion = orden.getFechaCreacion();
		for (DetalleOrden detalleOrden : orden.getDetallesOrden())detallesOrdenDto.add(new DetalleOrdenDTO(detalleOrden));
		this.clienteDto = new ClienteSimpleDTO(orden.getCliente());
	}

}
