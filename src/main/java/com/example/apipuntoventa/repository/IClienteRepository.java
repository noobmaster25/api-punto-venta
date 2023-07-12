package com.example.apipuntoventa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	Optional<Cliente> findByCorreo(String correo);
	Page<Cliente> findAllByNombreContaining(String nombre, Pageable pageable);
}
