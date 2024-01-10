package com.pingosystem.barriga.service;

import java.util.Optional;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario salvar(Usuario usuario) {
		usuarioRepository.getUserByEmail(usuario.email()).ifPresent(user -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.email()));
		});
		
		return usuarioRepository.salvar(usuario);
	}
	
	public Optional<Usuario> getUserByEmail(String email){
		return usuarioRepository.getUserByEmail(email);
	}
}