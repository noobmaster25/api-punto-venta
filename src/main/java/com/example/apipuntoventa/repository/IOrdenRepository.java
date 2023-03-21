package com.example.apipuntoventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

}
