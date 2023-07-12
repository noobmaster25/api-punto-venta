package com.example.apipuntoventa.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.ProductoDTO;
import com.example.apipuntoventa.dto.ProductoNuevoDTO;
import com.example.apipuntoventa.entities.Categoria;
import com.example.apipuntoventa.entities.Producto;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.ICategoriaRepository;
import com.example.apipuntoventa.repository.IProductoRepository;
import com.example.apipuntoventa.service.IProductoService;
import com.example.apipuntoventa.specifications.ProductoSpecification;

@Service
public class ProductoServiceImpl implements IProductoService {

	private ProductoSpecification productoEspecificacion;

	private IProductoRepository productoRepo;

	private ICategoriaRepository categoriaRepo;
	
	public ProductoServiceImpl(ProductoSpecification productoEspecificacion, IProductoRepository productoRepo,
			ICategoriaRepository categoriaRepo) {
		this.productoEspecificacion = productoEspecificacion;
		this.productoRepo = productoRepo;
		this.categoriaRepo = categoriaRepo;
	}

	@Override
	public Page<ProductoDTO> obtenerProductos(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Producto> productos = productoRepo.findAll(pageable);
		return productos.map(p -> new ProductoDTO(p));
	}

	@Override
	public ProductoDTO obtenerPorId(Integer id) {
		Producto producto = productoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("producto con id " + id + " no existe"));
		return new ProductoDTO(producto);
	}

	@Override
	public Page<ProductoDTO> filtroProductoPorCategoriaYRangoPrecio(String categoria, Double precioMinimo,
			Double precioMaximo, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Specification<Producto> especificacionProducto = productoEspecificacion.filtrarProducto(categoria, precioMinimo, precioMaximo);

		Page<Producto> productos = productoRepo.findAll(especificacionProducto, pageable);

		return productos.map(p -> new ProductoDTO(p));
	}

	@Override
	public Page<ProductoDTO> buscarPorNombreOrDescripcion(String query, int page, int size) {
		Pageable pagebale = PageRequest.of(page, size);
		Page<Producto> productos = productoRepo.findAllByNombreContainingOrDescripcionContaining(query, query, pagebale);
		return productos.map(p->new ProductoDTO(p));
	}
	
	@Override
	public ProductoDTO guardarProducto(ProductoNuevoDTO productoNuevoDto) {
		Optional<Categoria> categoriaOptional = categoriaRepo.findById(productoNuevoDto.getCategoriaId());
		if (categoriaOptional.isEmpty())
			new NotFoundException("producto con id " + productoNuevoDto.getCategoriaId() + " no existe");

		Producto productoNuevo = new Producto(productoNuevoDto.getNombre(), productoNuevoDto.getDescripcion(),
				productoNuevoDto.getCantidad(), productoNuevoDto.getPrecio(), categoriaOptional.get());

		return new ProductoDTO(productoRepo.save(productoNuevo));

	}

	@Override
	public ProductoDTO actualizarProducto(Integer id, ProductoNuevoDTO productoNuevoDto) {
		Producto producto = productoRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("producto con id " + id + " no encontrado"));
		Categoria categoria = categoriaRepo.findById(productoNuevoDto.getCategoriaId())
				.orElseThrow(() -> new NotFoundException(
						"categoria con id " + productoNuevoDto.getCategoriaId() + " no encontrado"));

		producto.setCantidad(productoNuevoDto.getCantidad());
		producto.setDescripcion(productoNuevoDto.getDescripcion());
		producto.setNombre(productoNuevoDto.getNombre());
		producto.setPrecio(productoNuevoDto.getPrecio());
		producto.setCategoria(categoria);

		return new ProductoDTO(productoRepo.save(producto));
	}

	@Override
	public void eliminarPorId(Integer id) {
		productoRepo.findById(id).orElseThrow(() -> new NotFoundException("producto con id " + id + " no existe"));
		productoRepo.deleteById(id);
	}

	

}
