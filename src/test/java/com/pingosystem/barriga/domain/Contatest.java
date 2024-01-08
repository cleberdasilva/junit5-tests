package com.pingosystem.barriga.domain;

import static com.pingosystem.barriga.domain.builders.ContaBuilder.umaConta;
import static com.pingosystem.barriga.domain.builders.UsuarioBuilder.umUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
}