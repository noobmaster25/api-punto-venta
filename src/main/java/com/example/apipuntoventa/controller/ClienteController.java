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

import com.example.apipuntoventa.dto.ClienteDTO;
import com.example.apipuntoventa.dto.ClienteNuevoDTO;
import com.example.apipuntoventa.service.IClienteService;

@RestController
@RequestMapping("/punto-venta-api/v0/clientes")
@PreAuthorize("authenticated")
public class ClienteController {
	
	private IClienteService clienteService;

	public ClienteController(IClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> obtenerClientes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(clienteService.obtenerClientes(page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(clienteService.obtenerPorId(id));
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/search")
	public ResponseEntity<Page<ClienteDTO>> obtenerPorNombre(@RequestParam(defaultValue = "",required=false) String query,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(clienteService.obtenerClientesPorNombre(query, page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ClienteDTO> guardarCliente(@Valid @RequestBody ClienteNuevoDTO clienteNuevoDto) {
		return new ResponseEntity<>(clienteService.guardarCliente(clienteNuevoDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Integer id,
			@Valid @RequestBody ClienteNuevoDTO clienteNuevoDto) {
		return ResponseEntity.ok(clienteService.actualizarCliente(id, clienteNuevoDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		clienteService.eliminarClientePorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
