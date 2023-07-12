package com.example.apipuntoventa.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.DetalleOrdenDTO;
import com.example.apipuntoventa.dto.DetalleOrdenNuevaDTO;
import com.example.apipuntoventa.entities.DetalleOrden;
import com.example.apipuntoventa.entities.Orden;
import com.example.apipuntoventa.entities.Producto;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.IDetalleOrdenRepository;
import com.example.apipuntoventa.repository.IOrdenRepository;
import com.example.apipuntoventa.repository.IProductoRepository;
import com.example.apipuntoventa.service.IDetalleOrdenService;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	private IDetalleOrdenRepository detalleOrdenRepo;

	private IProductoRepository productoRepo;

	private IOrdenRepository ordenRepo;
	
	public DetalleOrdenServiceImpl(IDetalleOrdenRepository detalleOrdenRepo, IProductoRepository productoRepo,
			IOrdenRepository ordenRepo) {
		this.detalleOrdenRepo = detalleOrdenRepo;
		this.productoRepo = productoRepo;
		this.ordenRepo = ordenRepo;
	}

	@Override
	public Page<DetalleOrdenDTO> obtenerDetallesOrden(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<DetalleOrden> detallesOrden = detalleOrdenRepo.findAll(pageable);
		return detallesOrden.map(d -> new DetalleOrdenDTO(d));
	}

	@Override
	public DetalleOrdenDTO obtenerPorId(Integer id) {
		DetalleOrden detalleOrden = detalleOrdenRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("detalle con id " + id + " no encontrado"));
		return new DetalleOrdenDTO(detalleOrden);
	}

	@Override
	public DetalleOrdenDTO guardarDetalleOrden(DetalleOrdenNuevaDTO detalleOrdenNuevaDto) {
		Producto producto = obtenerProductoPorId(detalleOrdenNuevaDto.getProductoId());
		Orden orden = obtenerOrdenPorId(detalleOrdenNuevaDto.getOrdenId());

		if (producto == null)
			throw new NotFoundException("producto con id " + detalleOrdenNuevaDto.getProductoId() + " no existe");
		if (orden == null)
			throw new NotFoundException("orde con id " + detalleOrdenNuevaDto.getOrdenId() + " no existe");

		DetalleOrden detalleOrdenNueva = new DetalleOrden(detalleOrdenNuevaDto.getNombre(),
				detalleOrdenNuevaDto.getPrecio(), detalleOrdenNuevaDto.getCantidad(), detalleOrdenNuevaDto.getTotal(),
				orden, producto);

		return new DetalleOrdenDTO(detalleOrdenRepo.save(detalleOrdenNueva));

	}

	@Override
	public DetalleOrdenDTO actualizarOrden(Integer id, DetalleOrdenNuevaDTO detalleOrdenNuevaDto) {
		DetalleOrden detalleOrden = detalleOrdenRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("detalle con id " + id + " no encontrado"));
		Producto producto = productoRepo.findById(detalleOrdenNuevaDto.getProductoId())
				.orElseThrow(() -> new NotFoundException(
						"producto con id " + detalleOrdenNuevaDto.getProductoId() + " no encontrado"));
		Orden orden = ordenRepo.findById(detalleOrdenNuevaDto.getOrdenId()).orElseThrow(
				() -> new NotFoundException("orden con id " + detalleOrdenNuevaDto.getOrdenId() + " no encontrado"));

		detalleOrden.setCantidad(detalleOrdenNuevaDto.getCantidad());
		detalleOrden.setNombre(detalleOrdenNuevaDto.getNombre());
		detalleOrden.setPrecio(detalleOrdenNuevaDto.getPrecio());
		detalleOrden.setTotal(detalleOrdenNuevaDto.getTotal());

		detalleOrden.setOrden(orden);
		detalleOrden.setProducto(producto);
		return new DetalleOrdenDTO(detalleOrdenRepo.save(detalleOrden));

	}

	@Override
	public void eliminarDetallePorId(Integer id) {
		detalleOrdenRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("detalle con id " + id + " no encontrado"));
		detalleOrdenRepo.deleteById(id);
	}

	@Override
	public Producto obtenerProductoPorId(Integer id) {
		Optional<Producto> producto = productoRepo.findById(id);
		return producto.isPresent() ? producto.get() : null;
	}

	@Override
	public Orden obtenerOrdenPorId(Integer id) {
		Optional<Orden> orden = ordenRepo.findById(id);
		return orden.isPresent() ? orden.get() : null;
	}
}
