package com.example.apipuntoventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.DetalleOrden;

@Repository
public interface IDetalleOrdenRepository  extends JpaRepository<DetalleOrden, Integer>{

}
