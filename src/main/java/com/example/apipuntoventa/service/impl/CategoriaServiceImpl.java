package com.example.apipuntoventa.service.impl;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.CategoriaDTO;
import com.example.apipuntoventa.dto.CategoriaNuevaDTO;
import com.example.apipuntoventa.entities.Categoria;
import com.example.apipuntoventa.exceptions.ConflictException;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.ICategoriaRepository;
import com.example.apipuntoventa.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

	private ICategoriaRepository categoriaRepo;
	
	public CategoriaServiceImpl(ICategoriaRepository categoriaRepo) {
		this.categoriaRepo = categoriaRepo;
	}

	@Override
	public Page<CategoriaDTO> obtenerCategorias(int page, int size) {
		Pageable pagebale = PageRequest.of(page, size);
		Page<Categoria> categorias = categoriaRepo.findAll(pagebale);
		return categorias.map(c -> new CategoriaDTO(c));
	}

	@Override
	public Page<CategoriaDTO> buscarPorNombreOrDescripcion(String query, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Categoria> categorias = categoriaRepo.findAllByNombreContainingOrDescripcionContaining(query, query, pageable);
		return categorias.map(c->new CategoriaDTO(c));
	}
	
	@Override
	public CategoriaDTO obtenerPorId(Integer id) {
		Categoria categoria = categoriaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("categoria con id " + id + " no encontrado"));
		return new CategoriaDTO(categoria);
	}

	@Override
	public CategoriaDTO guardarCategoria(CategoriaNuevaDTO categoriaNuevaDto) {
		Categoria categoriaNueva = new Categoria(categoriaNuevaDto.getNombre(), categoriaNuevaDto.getDescripcion());
		categoriaRepo.save(categoriaNueva);
		return new CategoriaDTO(categoriaNueva);
	}

	@Override
	public CategoriaDTO actualizarCategoria(Integer id, CategoriaNuevaDTO categoriaNuevaDto) {
		Categoria categoria = categoriaRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("categoria con id " + id + " no encontrado"));

		categoria.setNombre(categoriaNuevaDto.getNombre());
		categoria.setDescripcion(categoriaNuevaDto.getDescripcion());

		return new CategoriaDTO(categoriaRepo.save(categoria));
	}

	@Override
	public void eliminarCategoriaPorId(Integer id) {
		if (!existeCategoriaPorId(id))
			throw new NotFoundException("categoria con id " + id + " no encontrado");
		
		try {
			categoriaRepo.deleteById(id);			
		} catch (DataIntegrityViolationException ex) {
			throw new ConflictException("Esta categoria no se puede eliminar, existen elemetnos asociados a ella");
		}
	}

	@Override
	public boolean existeCategoriaPorId(Integer id) {
		Optional<Categoria> categoria = categoriaRepo.findById(id);
		return categoria.isEmpty() ? false : true;
	}

	

}
