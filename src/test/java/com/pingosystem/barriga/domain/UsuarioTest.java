package com.pingosystem.barriga.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//@DisplayName("Domínio: Usuario")
public class UsuarioTest {
	
	@Test
//	@DisplayName("Deve criar um suário válido")
	public void deveCriarUsuarioValido() {
		Usuario usario = new Usuario(1L, "Usuario Valido", "user@gmail.com", "123456");
		
		assertEquals(1L, usario.id());
		assertEquals("Usuario Valido", usario.nome());
		assertEquals("user@gmail.com", usario.email());
		assertEquals("123456", usario.senha());
	}

}
