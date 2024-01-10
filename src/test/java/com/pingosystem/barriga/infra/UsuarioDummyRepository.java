package com.pingosystem.barriga.infra;

import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;

import java.util.Optional;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

public class UsuarioDummyRepository implements UsuarioRepository {

	@Override
	public Usuario salvar(Usuario usuario) {
		return umUsuario()
				.comNome(usuario.nome())
				.comEmail(usuario.email())
				.comSenha(usuario.senha())
				.agora();
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		if (("user@gmail.com").equals(email))
			return Optional.of(umUsuario().comEmail(email).agora());
		return Optional.empty();
	}

}
