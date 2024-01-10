package com.pingosystem.barriga.service;

import org.junit.jupiter.api.Test;

public class UsuarioServiceTest {
	private UsuarioService service;
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		
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
