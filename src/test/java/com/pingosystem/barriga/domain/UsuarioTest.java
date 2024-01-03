package com.pingosystem.barriga.domain;

import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pingosystem.barriga.domain.exceptions.ValidationException;

//@DisplayName("Domínio: Usuario")
public class UsuarioTest {
	
	@Test
//	@DisplayName("Deve criar um suário válido")
	public void deveCriarUsuarioValido() {
		Usuario usuario = umUsuario().agora();
		
		Assertions.assertAll("Usuario", 
				() -> assertEquals(1L, usuario.id()),
				() -> assertEquals("Usuário Válido", usuario.nome()),
				() -> assertEquals("user@gmail.com", usuario.email()),
				() -> assertEquals("12345678", usuario.senha())
		);
	}
	
	@Test
	public void deveRejeitarUsuarioSemNome() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comNome(null).agora());
		assertEquals("Name is riquired", ex.getMessage());
	}
	
	@Test
	public void deveRejeitarUsuarioSemEmail() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comEmail(null).agora());
		assertEquals("Email is riquired", ex.getMessage());
	}
	
	@Test
	public void deveRejeitarUsuarioSemSenha() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comSenha(null).agora());
		assertEquals("Password is riquired", ex.getMessage());
	}
}
