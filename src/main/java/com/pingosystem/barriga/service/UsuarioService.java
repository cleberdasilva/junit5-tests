package com.pingosystem.barriga.service;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.salvar(usuario);
	}
	
	
}
