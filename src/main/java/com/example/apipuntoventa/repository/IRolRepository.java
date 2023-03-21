package com.example.apipuntoventa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByNombre(String nombre);
}
