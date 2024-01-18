package com.pingosystem.barriga.service;

import static com.pingosystem.barriga.domain.builders.ContaBuilder.umaConta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	@InjectMocks
	private ContaService contaService;
	
	@Mock
	private ContaRepository contaRepository;
	
	@Test
	public void deveSalvarComSucesso() {
		Conta contaToSave = umaConta().comId(null).agora();
		
		Mockito.when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().agora());
		
		Conta savedConta = contaService.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
	}

	
}
