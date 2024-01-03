package com.pingosystem.barriga.domain;

import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsurio;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pingosystem.barriga.domain.exceptions.ValidationException;

//@DisplayName("Domínio: Usuario")
public class UsuarioTest {
	
	@Test
//	@DisplayName("Deve criar um suário válido")
	public void deveCriarUsuarioValido() {
		Usuario usario = umUsurio().agora();
		
		Assertions.assertAll("Usuario", 
				() -> assertEquals(1L, usario.id()),
				() -> assertEquals("Usuário Válido", usario.nome()),
				() -> assertEquals("user@gmail.com", usario.email()),
				() -> assertEquals("12345678", usario.senha())
		);
	}
	
	@Test
	public void deveRejeitarUsuarioSemNome() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsurio().comNome(null).agora());
		assertEquals("Name is riquired", ex.getMessage());
	}
}
