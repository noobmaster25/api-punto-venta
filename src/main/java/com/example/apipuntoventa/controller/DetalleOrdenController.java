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

import com.example.apipuntoventa.dto.DetalleOrdenDTO;
import com.example.apipuntoventa.dto.DetalleOrdenNuevaDTO;
import com.example.apipuntoventa.service.IDetalleOrdenService;

@RestController
@RequestMapping("/punto-venta-api/v0/detalles-orden")
@PreAuthorize("authenticated")
public class DetalleOrdenController {

	private IDetalleOrdenService detalleOrdenService;

	public DetalleOrdenController(IDetalleOrdenService detalleOrdenService) {
		this.detalleOrdenService = detalleOrdenService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<DetalleOrdenDTO>> obtenerDetallesOrden(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(detalleOrdenService.obtenerDetallesOrden(page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<DetalleOrdenDTO> obtenerPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(detalleOrdenService.obtenerPorId(id));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<DetalleOrdenDTO> guardarDetalleOrden(
			@Valid @RequestBody DetalleOrdenNuevaDTO detalleOrdenNuevaDto) {
		return new ResponseEntity<>(detalleOrdenService.guardarDetalleOrden(detalleOrdenNuevaDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<DetalleOrdenDTO> actualizarDetalleOrden(@PathVariable Integer id,
			@Valid @RequestBody DetalleOrdenNuevaDTO detalleOrdenNuevaDto) {
		return ResponseEntity.ok(detalleOrdenService.actualizarOrden(id, detalleOrdenNuevaDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		detalleOrdenService.eliminarDetallePorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
