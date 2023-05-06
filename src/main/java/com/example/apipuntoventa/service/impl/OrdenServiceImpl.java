package com.example.apipuntoventa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.DetalleOrdenNuevaDTO;
import com.example.apipuntoventa.dto.OrdenActualizadaDTO;
import com.example.apipuntoventa.dto.OrdenDTO;
import com.example.apipuntoventa.dto.OrdenNuevaDTO;
import com.example.apipuntoventa.entities.Cliente;
import com.example.apipuntoventa.entities.DetalleOrden;
import com.example.apipuntoventa.entities.Orden;
import com.example.apipuntoventa.entities.Producto;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.IClienteRepository;
import com.example.apipuntoventa.repository.IDetalleOrdenRepository;
import com.example.apipuntoventa.repository.IOrdenRepository;
import com.example.apipuntoventa.repository.IProductoRepository;
import com.example.apipuntoventa.service.IOrdenService;

@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenRepository ordenRepo;

	@Autowired
	private IClienteRepository clienteRepo;

	@Autowired
	private IProductoRepository productoRepo;

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepo;

	@Override
	public Page<OrdenDTO> obtenerOrdenes(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Orden> ordenes = ordenRepo.findAll(pageable);
		return ordenes.map(o -> new OrdenDTO(o));
	}

	@Override
	public OrdenDTO obtenerOrdenPorId(Integer id) {
		Orden orden = ordenRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("orden con id " + id + " no existe"));
		return new OrdenDTO(orden);
	}

	@Override
	public OrdenDTO guardarOrden(OrdenNuevaDTO ordenNuevaDto) {
		Cliente cliente = obtenerClientePorId(ordenNuevaDto.getClienteId());
		if (cliente == null)
			throw new NotFoundException("cliente con id " + ordenNuevaDto.getClienteId() + " no existe");

		Orden ordenNueva = mapearOrdenDeNuevaOrdenDTO(ordenNuevaDto, cliente);
		ordenRepo.save(ordenNueva);

		List<DetalleOrden> detalles = new ArrayList<>();
		for (DetalleOrdenNuevaDTO detalleOrdenNuevaDto : ordenNuevaDto.getDetallesOrdenNuevaDto()) {
			Producto producto = obtenerProductoPorId(detalleOrdenNuevaDto.getProductoId());
			if (producto == null)
				throw new NotFoundException("producto con id " + detalleOrdenNuevaDto.getProductoId() + " no existe");

			DetalleOrden detalleOrdenNueva = mapearDetalleOrden(detalleOrdenNuevaDto, ordenNueva, producto);
			detalleOrdenRepo.save(detalleOrdenNueva);

			detalles.add(detalleOrdenNueva);
		}
		ordenNueva.setDetallesOrden(detalles);

		return new OrdenDTO(ordenNueva);
	}

	@Override
	public OrdenDTO actualizarOrden(Integer id, OrdenActualizadaDTO ordenActualizadaDto) {
		Orden orden = ordenRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("orden con id " + id + " no existe"));
		Cliente cliente = clienteRepo.findById(ordenActualizadaDto.getClienteId()).orElseThrow(
				() -> new NotFoundException("cliente con id " + ordenActualizadaDto.getClienteId() + " no existe"));

		orden.setNumeroOrden(ordenActualizadaDto.getNumeroOrden());
		orden.setFechaCreacion(ordenActualizadaDto.getFechaCreacion());
		orden.setCliente(cliente);
		return new OrdenDTO(ordenRepo.save(orden));

	}

	@Override
	public void eliminarPorId(Integer id) {
		ordenRepo.findById(id).orElseThrow(() -> new NotFoundException("orden con id " + id + " no encontrado"));
		ordenRepo.deleteById(id);

	}

	@Override
	public Cliente obtenerClientePorId(Integer id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.isPresent() ? cliente.get() : null;
	}

	@Override
	public Producto obtenerProductoPorId(Integer id) {
		Optional<Producto> producto = productoRepo.findById(id);
		return producto.isPresent() ? producto.get() : null;
	}

	private DetalleOrden mapearDetalleOrden(DetalleOrdenNuevaDTO detalleOrdenNuevaDto, Orden orden, Producto producto) {
		DetalleOrden detalleOrdenNueva = new DetalleOrden(detalleOrdenNuevaDto.getNombre(),
				detalleOrdenNuevaDto.getPrecio(), detalleOrdenNuevaDto.getCantidad(), detalleOrdenNuevaDto.getTotal(),
				orden, producto);

		return detalleOrdenNueva;
	}

	private Orden mapearOrdenDeNuevaOrdenDTO(OrdenNuevaDTO ordenNuevaDto, Cliente cliente) {
		Orden ordenNueva = new Orden(ordenNuevaDto.getNumeroOrden(), ordenNuevaDto.getFechaCreacion(), cliente);

		return ordenNueva;
	}
}
