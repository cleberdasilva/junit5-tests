package com.pingosystem.barriga.domain;

import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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
		assertEquals("Name is required", ex.getMessage());
	}
	
	@Test
	public void deveRejeitarUsuarioSemEmail() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comEmail(null).agora());
		assertEquals("Email is required", ex.getMessage());
	}
	
	@Test
	public void deveRejeitarUsuarioSemSenha() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comSenha(null).agora());
		assertEquals("Password is required", ex.getMessage());
	}
	
	//with @ParameterizedTest all the tests above aren't necessary
	
	
//	@CsvSource(value = {
//			"1, NULL, user@gmail.com, 12345678, Name is required",
//			"1, Nome Usuario, NULL, 12345678, Email is required",
//			"1, Nome Usuario, user@gmail.com, NULL, Password is required"
//	}, nullValues = "NULL")
//	@ParameterizedTest(name = "[{index}] -{4}") //index and test name will appear at test execution
//	@CsvFileSource(files = "src\\test\\resources\\camposObrigatoriosUsuario.csv", nullValues = "NULL", numLinesToSkip = 1)
	@ParameterizedTest
	@CsvFileSource(files = "src\\test\\resources\\camposObrigatoriosUsuario.csv", nullValues = "NULL", useHeadersInDisplayName = true)
	public void deveValidarCamposObrigatorios(Long id, String nome, String email, String senha, String mensagem) {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () ->
		umUsuario().comId(id).comNome(nome).comEmail(email).comSenha(senha).agora());
		assertEquals(mensagem, ex.getMessage());
	}
}
