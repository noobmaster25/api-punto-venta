package com.example.apipuntoventa.service;

import org.springframework.data.domain.Page;

import com.example.apipuntoventa.dto.ActualizarUsuarioDtO;
import com.example.apipuntoventa.dto.UsuarioDTO;

public interface IUsuarioService {

	Page<UsuarioDTO> obtenerTodos(int page, int size);

	UsuarioDTO obtenerPorId(Integer id);

	UsuarioDTO actualizarUsuario(Integer id, ActualizarUsuarioDtO actualizarUsuarioDto);

	void eliminarUsuario(Integer id);

}
