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
		UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioService(usuarioRepository);
		
		Mockito.when(usuarioRepository.getUserByEmail("mail@gmail.com"))
		.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()), Optional.of(UsuarioBuilder.umUsuario().agora()), null);
		
		Optional<Usuario> user = service.getUserByEmail("mail@gmail.com");
		System.out.println(user);
		Assertions.assertTrue(user.isPresent());
		user = service.getUserByEmail("mail123123123@gmail.com");
		System.out.println(user);
		user = service.getUserByEmail("mail@gmail.com");
		System.out.println(user);
		user = service.getUserByEmail("mail@gmail.com");
		System.out.println(user);
		
		Mockito.verify(usuarioRepository, Mockito.times(3)).getUserByEmail("mail@gmail.com");
		Mockito.verify(usuarioRepository, Mockito.times(1)).getUserByEmail("mail123123123@gmail.com");
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).getUserByEmail("mail@gmail.com");
		Mockito.verify(usuarioRepository, Mockito.never()).getUserByEmail("other@gmail.com");
		Mockito.verifyNoMoreInteractions(usuarioRepository);
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
