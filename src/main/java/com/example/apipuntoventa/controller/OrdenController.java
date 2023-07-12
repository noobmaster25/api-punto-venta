package com.example.apipuntoventa.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipuntoventa.dto.OrdenActualizadaDTO;
import com.example.apipuntoventa.dto.OrdenDTO;
import com.example.apipuntoventa.dto.OrdenNuevaDTO;
import com.example.apipuntoventa.service.IOrdenService;

@RestController
@RequestMapping("/punto-venta-api/v0/ordenes")
@PreAuthorize("authenticated")
public class OrdenController {

	private IOrdenService ordenService;
	
	public OrdenController(IOrdenService ordenService) {
		this.ordenService = ordenService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<OrdenDTO>> obtenerOrdenes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(ordenService.obtenerOrdenes(page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<OrdenDTO> obtenerOrdenPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<OrdenDTO> guardarOrden(@Valid @RequestBody OrdenNuevaDTO ordenNuevaDto) {
		System.out.println(ordenNuevaDto);
		return new ResponseEntity<>(ordenService.guardarOrden(ordenNuevaDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<OrdenDTO> actualizarOrden(@PathVariable Integer id,
			@Valid @RequestBody OrdenActualizadaDTO ordenActualizadaDto) {
		return ResponseEntity.ok(ordenService.actualizarOrden(id, ordenActualizadaDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		ordenService.eliminarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
