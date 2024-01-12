package com.pingosystem.barriga.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.domain.builders.UsuarioBuilder;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

public class UsuarioServiceTest {
	private UsuarioService service;
	
	@Test
	public void deveRetornarEmptyQuadoUsuarioInexistente() {
		UsuarioRepository usarioRepository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioService(usarioRepository);
		
		Mockito.when(usarioRepository.getUserByEmail("mail@gmail.com")).thenReturn(Optional.empty());
		
		Optional<Usuario> user = service.getUserByEmail("mail@gmail.com");
		Assertions.assertTrue(user.isEmpty());
	}
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		UsuarioRepository usarioRepository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioService(usarioRepository);
		
		Mockito.when(usarioRepository.getUserByEmail("mail@gmail.com"))
		.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		
		Optional<Usuario> user = service.getUserByEmail("mail@gmail.com");
		Assertions.assertFalse(user.isEmpty());
	}
	
	
//	Test using dummyClass
	/*@Test
	public void deveSalvarUsuarioComSucesso() {
		service = new UsuarioService(new UsuarioDummyRepository());
		Usuario user = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
		Usuario saveUser = service.salvar(user);
		Assertions.assertNotNull(saveUser.id());
	}*/
}
