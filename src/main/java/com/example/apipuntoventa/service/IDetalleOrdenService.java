package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.DetalleOrdenDTO;
import com.example.apipuntoventa.dto.DetalleOrdenNuevaDTO;
import com.example.apipuntoventa.entities.Orden;
import com.example.apipuntoventa.entities.Producto;

public interface IDetalleOrdenService {

	Page<DetalleOrdenDTO> obtenerDetallesOrden(int page, int size);

	DetalleOrdenDTO obtenerPorId(Integer id);

	DetalleOrdenDTO guardarDetalleOrden(DetalleOrdenNuevaDTO detalleOrdenNuevaDto);

	DetalleOrdenDTO actualizarOrden(Integer id, DetalleOrdenNuevaDTO detalleOrdenNuevaDto);

	void eliminarDetallePorId(Integer id);

	Producto obtenerProductoPorId(Integer id);

	Orden obtenerOrdenPorId(Integer id);

}
