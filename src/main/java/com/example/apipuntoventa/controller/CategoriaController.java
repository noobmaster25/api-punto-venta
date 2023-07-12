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

import com.example.apipuntoventa.dto.CategoriaDTO;
import com.example.apipuntoventa.dto.CategoriaNuevaDTO;
import com.example.apipuntoventa.service.ICategoriaService;

@RestController
@RequestMapping("/punto-venta-api/v0/categorias")
@PreAuthorize("authenticated")
public class CategoriaController {

	private ICategoriaService categoriaService;

	public CategoriaController(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<CategoriaDTO>> obtenerCategorias(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(categoriaService.obtenerCategorias(page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/search")
	public ResponseEntity<Page<CategoriaDTO>> buscarPorNombreOrDescripcion(
			@RequestParam(defaultValue = "", required = false) String query, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(categoriaService.buscarPorNombreOrDescripcion(query, page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(categoriaService.obtenerPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoriaDTO> guardarCategoria(@Valid @RequestBody CategoriaNuevaDTO categoriaNuevaDto) {
		return new ResponseEntity<>(categoriaService.guardarCategoria(categoriaNuevaDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable Integer id,
			@Valid @RequestBody CategoriaNuevaDTO categoriaNuevaDto) {
		return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoriaNuevaDto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		categoriaService.eliminarCategoriaPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
