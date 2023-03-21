package com.example.apipuntoventa.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orden_id")
	private Integer id;

	@Column(name = "numero_orden")
	private Integer numeroOrden;

	@Column(name = "fecha_creacion")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
	@OneToMany(mappedBy = "orden")
	private List<DetalleOrden> detallesOrden;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Orden(Integer numeroOrden, LocalDateTime fechaCreacion, List<DetalleOrden> detallesOrden, Cliente cliente) {
		this.numeroOrden = numeroOrden;
		this.fechaCreacion = fechaCreacion;
		this.detallesOrden = detallesOrden;
		this.cliente = cliente;
	}

	public Orden(Integer numeroOrden, LocalDateTime fechaCreacion, Cliente cliente) {
		this.numeroOrden = numeroOrden;
		this.fechaCreacion = fechaCreacion;
		this.cliente = cliente;
	}
	
	
	

}
