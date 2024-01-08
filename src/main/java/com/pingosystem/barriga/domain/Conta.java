package com.pingosystem.barriga.domain;

import com.pingosystem.barriga.domain.exceptions.ValidationException;

public class Conta {
	private Long id;
	private String nome;
	private Usuario usuario;
	
	
	public Conta(Long id, String nome, Usuario usuario) {
		if (nome == null) throw new ValidationException("Name is required");
		if (usuario == null) throw new ValidationException("User is required");
		
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Long id() {
		return id;
	}

	public String nome() {
		return nome;
	}

	public Usuario usuario() {
		return usuario;
	}
}
