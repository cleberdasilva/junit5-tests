package com.pingosystem.barriga.domain;

import static com.pingosystem.barriga.domain.builders.ContaBuilder.umaConta;
import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.pingosystem.barriga.domain.exceptions.ValidationException;

public class Contatest {

	@Test
	public void deveCriarContaValida(){
		Conta conta = umaConta().agora();
		
		Assertions.assertAll("Conta",
				() -> Assertions.assertEquals(1L, conta.id()),
				() -> Assertions.assertEquals("Conta Válida", conta.nome()),
				() -> Assertions.assertEquals(umUsuario().agora(), conta.usuario())
		);
	}
	
	@ParameterizedTest
	@MethodSource(value = "dataProvider")
	public void deveRejeitarContaInvalinda(Long id, String nome, Usuario usuario, String mensagem) {
		String errorMessage = Assertions.assertThrows(ValidationException.class, () ->
			umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
		
		Assertions.assertEquals(mensagem, errorMessage);
	}
	
	private static Stream<Arguments> dataProvider(){
		return Stream.of(
			Arguments.of(1L, null, umUsuario().agora(), "Name is required"),
			Arguments.of(1L, "Conta Válida", null, "User is required")
		);
	}
}