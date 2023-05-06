package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.OrdenActualizadaDTO;
import com.example.apipuntoventa.dto.OrdenDTO;
import com.example.apipuntoventa.dto.OrdenNuevaDTO;
import com.example.apipuntoventa.entities.Cliente;
import com.example.apipuntoventa.entities.Producto;

public interface IOrdenService {

	Page<OrdenDTO> obtenerOrdenes(int page, int size);

	OrdenDTO obtenerOrdenPorId(Integer id);

	OrdenDTO guardarOrden(OrdenNuevaDTO ordenNuevaDto);

	OrdenDTO actualizarOrden(Integer id, OrdenActualizadaDTO ordenActualizadaDto);

	void eliminarPorId(Integer id);

	Cliente obtenerClientePorId(Integer id);

	Producto obtenerProductoPorId(Integer id);
}
