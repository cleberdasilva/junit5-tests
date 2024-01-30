package com.pingosystem.barriga.service;

import static com.pingosystem.barriga.domain.builders.ContaBuilder.umaConta;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.external.ContaEvent;
import com.pingosystem.barriga.service.external.ContaEvent.EventType;
import com.pingosystem.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class ContaServiceTest {
	@InjectMocks
	private ContaService contaService;
	
	@Mock
	private ContaRepository contaRepository;
	
	@Mock
	private ContaEvent event;
	
	@Captor
	private ArgumentCaptor<Conta> contaCaptor;
	
	@Test
	public void deveSalvarPrimeiraContaComSucesso() throws Exception {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
		//void method
		Mockito.doNothing().when(event).dispatch(umaConta().agora(), EventType.CREATED);
		
		Conta savedConta = contaService.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
		
		Mockito.verify(contaRepository).salvar(contaCaptor.capture());
//		System.out.println("Captor: " + contaCaptor.getAllValues());
		Assertions.assertNull(contaCaptor.getValue().id());
		Assertions.assertTrue(contaCaptor.getValue().nome().startsWith("Conta Válida"));
	}
	
	@Test
	public void deveSalvarSegundaContaComSucesso() {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
			.thenReturn(Arrays.asList(umaConta().comNome("Outra Conta").agora()));
		Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
		
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
	
	@Test
	public void naoDeveManterContaSemEvento() throws Exception {
		Conta contaToSave = umaConta().comId(null).agora();
		Conta contaSalva = umaConta().agora();
		
		Mockito.when(contaRepository.salvar(Mockito.any(Conta.class))).thenReturn(contaSalva);
		//void method
		Mockito.doThrow(new Exception("Falha Catastrófica"))
			.when(event).dispatch(contaSalva, EventType.CREATED);
		
		String mensagem = Assertions.assertThrows(Exception.class, ()->
			contaService.salvar(contaToSave)).getMessage();
		Assertions.assertEquals("Falha na criação da conta, tente novamente", (mensagem));
		
		Mockito.verify(contaRepository).delete(contaSalva);
	}
}
