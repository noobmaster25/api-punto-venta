package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.ClienteDTO;
import com.example.apipuntoventa.dto.ClienteNuevoDTO;

public interface IClienteService {

	Page<ClienteDTO> obtenerClientes(int page, int size);
	
	Page<ClienteDTO> obtenerClientesPorNombre(String query, int page, int size);

	ClienteDTO guardarCliente(ClienteNuevoDTO clienteNuevoDto);

	ClienteDTO obtenerPorId(Integer id);

	ClienteDTO actualizarCliente(Integer id, ClienteNuevoDTO clienteNuevoDto);

	void eliminarClientePorId(Integer id);

}
