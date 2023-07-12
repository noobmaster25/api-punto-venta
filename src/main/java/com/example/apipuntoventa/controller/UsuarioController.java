package com.example.apipuntoventa.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipuntoventa.dto.ActualizarUsuarioDtO;
import com.example.apipuntoventa.dto.UsuarioDTO;
import com.example.apipuntoventa.service.IUsuarioService;

@RestController
@RequestMapping("/punto-venta-api/v0/usuarios")
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {

	private IUsuarioService usuarioService;
	
	public UsuarioController(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> obtenerUsuarios(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(usuarioService.obtenerTodos(page, size));
	}
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Integer id){
		return ResponseEntity.ok(usuarioService.obtenerPorId(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Integer id, @Valid @RequestBody ActualizarUsuarioDtO actualizarUsuarioDto){
		return ResponseEntity.ok(usuarioService.actualizarUsuario(id, actualizarUsuarioDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
		usuarioService.eliminarUsuario(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
