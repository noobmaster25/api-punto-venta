package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.CategoriaDTO;
import com.example.apipuntoventa.dto.CategoriaNuevaDTO;

public interface ICategoriaService {

	Page<CategoriaDTO> obtenerCategorias(int page, int size);

	Page<CategoriaDTO> buscarPorNombreOrDescripcion(String query, int page, int size);
	
	CategoriaDTO obtenerPorId(Integer id);

	CategoriaDTO guardarCategoria(CategoriaNuevaDTO categoriaNuevaDto);

	CategoriaDTO actualizarCategoria(Integer id, CategoriaNuevaDTO categoriaNuevaDto);

	void eliminarCategoriaPorId(Integer id);

	boolean existeCategoriaPorId(Integer id);
}
