package com.example.apipuntoventa.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "username", length = 20)
	private String userName;

	@Column(length = 30)
	private String nombre;

	private String apellido;

	private String email;

	private String contrasenia;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_rol", joinColumns = @JoinColumn(name="id_usuario"),inverseJoinColumns = @JoinColumn(name="id_rol"))
	List<Rol> roles;
}
