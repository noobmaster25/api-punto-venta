package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.ProductoDTO;
import com.example.apipuntoventa.dto.ProductoNuevoDTO;

public interface IProductoService {
	
	Page<ProductoDTO> obtenerProductos(int page, int size);
	
	ProductoDTO obtenerPorId(Integer id);
	
	Page<ProductoDTO> filtroProductoPorCategoriaYRangoPrecio(String categoria, Double precioMinimo, Double precioMaximo, int page, int size);
	
	ProductoDTO guardarProducto(ProductoNuevoDTO productoNuevoDto);
	
	ProductoDTO actualizarProducto(Integer id,ProductoNuevoDTO productoNuevoDto);
	
	void eliminarPorId(Integer id);
	
	

}
