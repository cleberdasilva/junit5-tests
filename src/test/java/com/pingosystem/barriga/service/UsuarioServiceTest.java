package com.pingosystem.barriga.service;

import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pingosystem.barriga.domain.Usuario;
import com.pingosystem.barriga.domain.builders.UsuarioBuilder;
import com.pingosystem.barriga.service.repositories.UsuarioRepository;

@Tag("service")
@Tag("usuario")
@ExtendWith(MockitoExtension.class) //With this annotation we don´t need the BeforeEach
public class UsuarioServiceTest {
	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioService service;
	
	@Test
	public void deveRetornarEmptyQuadoUsuarioInexistente() {
		Mockito.when(usuarioRepository.getUserByEmail("mail@gmail.com")).thenReturn(Optional.empty());
		
		Optional<Usuario> user = service.getUserByEmail("mail@gmail.com");
		Assertions.assertTrue(user.isEmpty());
	}
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		
		Mockito.when(usuarioRepository.getUserByEmail("mail@gmail.com"))
		.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		
		Optional<Usuario> user = service.getUserByEmail("mail@gmail.com");
		System.out.println(user);
		Assertions.assertTrue(user.isPresent());
		
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).getUserByEmail("mail@gmail.com");
		Mockito.verify(usuarioRepository, Mockito.never()).getUserByEmail("other@gmail.com");
		
	}
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		
		Usuario userToSave = umUsuario().comId(null).agora();
		
		Mockito.when(usuarioRepository.getUserByEmail(userToSave.email()))
			.thenReturn(Optional.empty());
		
		Mockito.when(usuarioRepository.salvar(userToSave)).thenReturn(umUsuario().agora());
		
		Usuario savedUser = service.salvar(userToSave);
		Assertions.assertNotNull(savedUser.id());
		
		Mockito.verify(usuarioRepository).getUserByEmail(userToSave.email());
		//next line it´s not necessary ´cause we are checking it at line 67
//		Mockito.verify(usuarioRepository).salvar(userToSave);
		
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
