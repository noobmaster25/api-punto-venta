package com.example.apipuntoventa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

	Page<Categoria> findAllByNombreContainingOrDescripcionContaining(String nombre, String descripcion, Pageable pageable);
}
