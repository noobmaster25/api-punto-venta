package com.example.apipuntoventa.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.apipuntoventa.entities.Categoria;
import com.example.apipuntoventa.entities.Producto;

@Component
public class ProductoSpecification {
	
	public Specification<Producto> filtrarProducto(String categoria,
													Double precioMinimo,
													Double precioMaximo){
		return (root, query, cb) -> {
			List<Predicate> predicados = new ArrayList<>();
			
			if (categoria != null && !categoria.isEmpty()) {
				Join<Producto, Categoria> joinCategoria = root.join("categoria");
				predicados.add(cb.equal(joinCategoria.get("nombre"), categoria));
			}
			if (precioMinimo != null && precioMinimo > 0) {
				predicados.add(cb.greaterThan(root.get("precio"), precioMinimo));
			}
			if (precioMaximo != null && precioMaximo < Integer.MAX_VALUE) {
				predicados.add(cb.lessThan(root.get("precio"), precioMaximo));
			}
			
			return cb.and(predicados.toArray(new Predicate[0]));
		};
	}
}
