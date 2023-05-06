package com.example.apipuntoventa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.apipuntoventa.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>, JpaSpecificationExecutor<Producto> {

}
