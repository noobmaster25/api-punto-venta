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

import com.example.apipuntoventa.dto.ProductoDTO;
import com.example.apipuntoventa.dto.ProductoNuevoDTO;
import com.example.apipuntoventa.service.IProductoService;

@RestController
@RequestMapping("/punto-venta-api/v0/productos")
@PreAuthorize("authenticated")
public class ProductoController {

	private IProductoService productoService;

	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<ProductoDTO>> obtenerProductos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(productoService.obtenerProductos(page, size));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(productoService.obtenerPorId(id));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/filtrar")
	public ResponseEntity<Page<ProductoDTO>> filtrarProductos(@RequestParam(required = false) String categoira,
																@RequestParam(name = "precio-minimo",required = false) Double precioMinimo,
																@RequestParam(name = "precio-max",required = false) Double precioMaximo,
																@RequestParam(defaultValue = "0") int page,
																@RequestParam(defaultValue = "10") int size){
		
		return ResponseEntity.ok(productoService.filtroProductoPorCategoriaYRangoPrecio(categoira, precioMinimo, precioMaximo, page, size));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/search")
	public ResponseEntity<Page<ProductoDTO>> buscarPorNombreOrDescripcion(@RequestParam(defaultValue = "",required = false) String query,
																			@RequestParam(defaultValue = "0") int page,
																			@RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(productoService.buscarPorNombreOrDescripcion(query, page, size));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ProductoDTO> guardarProducto(@Valid @RequestBody ProductoNuevoDTO productoNuevoDto) {
		return new ResponseEntity<>(productoService.guardarProducto(productoNuevoDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Integer id,
			@Valid @RequestBody ProductoNuevoDTO productoNuevoDto) {
		return ResponseEntity.ok(productoService.actualizarProducto(id, productoNuevoDto));
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPorId(@PathVariable Integer id) {
		productoService.eliminarPorId(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
