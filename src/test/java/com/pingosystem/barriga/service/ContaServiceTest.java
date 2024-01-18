package com.pingosystem.barriga.service;

import static com.pingosystem.barriga.domain.builders.ContaBuilder.umaConta;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class ContaServiceTest {
	@InjectMocks
	private ContaService contaService;
	
	@Mock
	private ContaRepository contaRepository;
	
	@Test
	public void deveSalvarPrimeiraContaComSucesso() {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().agora());
		
		Conta savedConta = contaService.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
	}
	
	@Test
	public void deveSalvarSegundaContaComSucesso() {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
			.thenReturn(Arrays.asList(umaConta().comNome("Outra Conta").agora()));
		Mockito.when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().agora());
		
		Conta savedConta = contaService.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
	}

	@Test
	public void deveRejeitarContaRepetida() {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
			.thenReturn(Arrays.asList(umaConta().agora()));
		
		String mensagem = Assertions.assertThrows(ValidationException.class, ()->
			contaService.salvar(contaToSave)).getMessage();
		Assertions.assertEquals("Usuário já possui uma conta com este nome", (mensagem));
	}
}
