package com.example.apipuntoventa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.apipuntoventa.dto.ClienteDTO;
import com.example.apipuntoventa.dto.ClienteNuevoDTO;
import com.example.apipuntoventa.entities.Cliente;
import com.example.apipuntoventa.exceptions.ConflictException;
import com.example.apipuntoventa.exceptions.NotFoundException;
import com.example.apipuntoventa.repository.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepo;

	public Page<ClienteDTO> obtenerClientes(int page, int size) {
		Pageable pagebale = PageRequest.of(page, size);
		Page<Cliente> clientes = clienteRepo.findAll(pagebale);
		return clientes.map(c -> new ClienteDTO(c));
	}

	public ClienteDTO guardarCliente(ClienteNuevoDTO clienteNuevoDto) {
		Optional<Cliente> clienteCorreo = clienteRepo.findByCorreo(clienteNuevoDto.getCorreo());
		if (clienteCorreo.isPresent())
			throw new ConflictException("correo " + clienteNuevoDto.getCorreo() + " ya existe");
		Cliente clienteNuevo = mapearCliente(clienteNuevoDto);		
		return new ClienteDTO(clienteRepo.save(clienteNuevo));
	}

	public ClienteDTO obtenerPorId(Integer id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("cliente con id " + id + "no existe"));
		return new ClienteDTO(cliente);
	}

	public ClienteDTO actualizarCliente(Integer id, ClienteNuevoDTO clienteNuevoDto) {
		Optional<Cliente> clienteCorreo = clienteRepo.findByCorreo(clienteNuevoDto.getCorreo());
		if (clienteCorreo.isPresent())
			throw new ConflictException("correo " + clienteNuevoDto.getCorreo() + " ya existe");

		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("cliente con id " + id + "no existe"));
		cliente.setNombre(clienteNuevoDto.getNombre());
		cliente.setDireccion(clienteNuevoDto.getDireccion());
		cliente.setTelefono(clienteNuevoDto.getTelefono());
		cliente.setTipo(clienteNuevoDto.getTipo());
		cliente.setCorreo(clienteNuevoDto.getCorreo());
		return new ClienteDTO(clienteRepo.save(cliente));

	}

	public void eliminarClientePorId(Integer id) {
		clienteRepo.findById(id).orElseThrow(() -> new NotFoundException("cliente con id " + id + "no existe"));
		clienteRepo.deleteById(id);
	}

	private Cliente mapearCliente(ClienteNuevoDTO clienteNuevoDto) {
		Cliente clienteNuevo = new Cliente(clienteNuevoDto.getNombre(), clienteNuevoDto.getTelefono(),
				clienteNuevoDto.getTipo(), clienteNuevoDto.getCorreo(), clienteNuevoDto.getDireccion());

		return clienteNuevo;
	}
}
