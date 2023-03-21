package com.example.apipuntoventa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.CategoriaDTO;
import com.example.apipuntoventa.dto.CategoriaNuevaDTO;
import com.example.apipuntoventa.entities.Categoria;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository categoriaRepo;

	public Page<CategoriaDTO> obtenerCategorias(int page, int size) {
		Pageable pagebale = PageRequest.of(page, size);
		Page<Categoria> categorias = categoriaRepo.findAll(pagebale);
		return categorias.map(c -> new CategoriaDTO(c));
	}

	public CategoriaDTO obtenerPorId(Integer id) {
		Categoria categoria = categoriaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("categoria con id " + id + " no encontrado"));
		return new CategoriaDTO(categoria);
	}

	public CategoriaDTO guardarCategoria(CategoriaNuevaDTO categoriaNuevaDto) {
		Categoria categoriaNueva = new Categoria(categoriaNuevaDto.getNombre(), categoriaNuevaDto.getDescripcion());
		categoriaRepo.save(categoriaNueva);
		return new CategoriaDTO(categoriaNueva);
	}

	public CategoriaDTO actualizarCategoria(Integer id, CategoriaNuevaDTO categoriaNuevaDto) {
		Categoria categoria = categoriaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("categoria con id " + id + " no encontrado"));

		categoria.setNombre(categoriaNuevaDto.getNombre());
		categoria.setDescripcion(categoriaNuevaDto.getDescripcion());
		
		return new CategoriaDTO(categoriaRepo.save(categoria));
	}

	public void eliminarCategoriaPorId(Integer id) {
		if (!existeCategoriaPorId(id))
			throw new NotFoundException("categoria con id " + id + " no encontrado");
		categoriaRepo.deleteById(id);
	}

	private boolean existeCategoriaPorId(Integer id) {
		Optional<Categoria> categoria = categoriaRepo.findById(id);
		return categoria.isEmpty() ? false : true;
	}

}
