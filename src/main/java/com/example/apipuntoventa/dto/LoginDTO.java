package com.example.apipuntoventa.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}
