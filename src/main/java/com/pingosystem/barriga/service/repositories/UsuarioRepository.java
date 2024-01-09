package com.pingosystem.barriga.service.repositories;

import java.util.Optional;

import com.pingosystem.barriga.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUserByEmail(String email);
}
