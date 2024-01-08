package com.pingosystem.barriga.domain;

import com.pingosystem.barriga.domain.exceptions.ValidationException;

public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public Usuario(Long id, String nome, String email, String senha) {
		
		if (nome == null) throw new ValidationException("Name is required");
		if (email == null) throw new ValidationException("Email is required");
		if (senha == null) throw new ValidationException("Password is required");
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Long id() {
		return id;
	}

	public String nome() {
		return nome;
	}

	public String email() {
		return email;
	}

	public String senha() {
		return senha;
	}
}
