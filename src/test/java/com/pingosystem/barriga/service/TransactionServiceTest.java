package com.pingosystem.barriga.service;

import static com.pingosystem.barriga.domain.builders.TransactionBuilder.umaTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.pingosystem.barriga.domain.Transaction;
import com.pingosystem.barriga.service.repositories.TransactionDao;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	
	@InjectMocks
	private TransactionService service;
	
	@Mock
	private TransactionDao dao;
	
	@Test
	public void deveSalvarTransacaoValida() {
		Transaction transacaoParaSalvar = umaTransaction().comId(null).agora();
		Transaction transacaoPersistida = umaTransaction().agora();
		Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(transacaoPersistida);
		
		Transaction transacaoSalva = service.salvar(transacaoParaSalvar);
//		Assertions.assertNotNull(transacaoSalva.getId());
		Assertions.assertEquals(transacaoPersistida ,transacaoSalva);
	}

}
