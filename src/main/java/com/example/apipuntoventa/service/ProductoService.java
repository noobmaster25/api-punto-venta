package com.example.apipuntoventa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.ProductoDTO;
import com.example.apipuntoventa.dto.ProductoNuevoDTO;
import com.example.apipuntoventa.entities.Categoria;
import com.example.apipuntoventa.entities.Producto;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.ICategoriaRepository;
import com.example.apipuntoventa.repository.IProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private IProductoRepository productoRepo;

	@Autowired
	private ICategoriaRepository categoriaRepo;

	public Page<ProductoDTO> obtenerProductos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Producto> productos = productoRepo.findAll(pageable);
		return productos.map(p -> new ProductoDTO(p));
	}

	public ProductoDTO obtenerPorId(Integer id) {
		Producto producto = productoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("producto con id " + id + " no existe"));
		return new ProductoDTO(producto);
	}

	public ProductoDTO guardarProducto(ProductoNuevoDTO productoNuevoDto) {
		Optional<Categoria> categoriaOptional = categoriaRepo.findById(productoNuevoDto.getCategoriaId());
		if (categoriaOptional.isEmpty())new NotFoundException("producto con id " + productoNuevoDto.getCategoriaId() + " no existe");
			

		Producto productoNuevo = new Producto(productoNuevoDto.getNombre(), productoNuevoDto.getDescripcion(),
				productoNuevoDto.getCantidad(), productoNuevoDto.getPrecio(), categoriaOptional.get());

		return new ProductoDTO(productoRepo.save(productoNuevo));

	}
	
	public ProductoDTO actualizarProducto(Integer id,ProductoNuevoDTO productoNuevoDto) {
		Producto producto = productoRepo.findById(id).orElseThrow(()-> new NotFoundException("producto con id "+id+" no encontrado"));
		Categoria categoria = categoriaRepo.findById(productoNuevoDto.getCategoriaId())
				.orElseThrow(()-> new NotFoundException("categoria con id "+productoNuevoDto.getCategoriaId()+" no encontrado"));
		
		producto.setCantidad(productoNuevoDto.getCantidad());
		producto.setDescripcion(productoNuevoDto.getDescripcion());
		producto.setNombre(productoNuevoDto.getNombre());
		producto.setPrecio(productoNuevoDto.getPrecio());
		producto.setCategoria(categoria);
		
		return new ProductoDTO(productoRepo.save(producto));
	}

	public void eliminarPorId(Integer id) {
		if (!existeProductoPorId(id))new NotFoundException("producto con id " + id + " no existe");
		productoRepo.deleteById(id);
	}

	private boolean existeProductoPorId(Integer id) {
		Optional<Producto> producto = productoRepo.findById(id);
		return producto.isEmpty() ? false : true;
	}
}
