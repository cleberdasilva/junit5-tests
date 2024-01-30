package com.pingosystem.barriga.service;

import com.pingosystem.barriga.domain.Transaction;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.repositories.TransactionDao;

public class TransactionService {
	private TransactionDao transactionDao;
	
	public Transaction salvar(Transaction transacao) {
		if (transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
		if (transacao.getValor() == null) throw new ValidationException("Valor inexistente");
		if (transacao.getData() == null) throw new ValidationException("Data inexistente");
		if (transacao.getConta() == null) throw new ValidationException("Conta inexistente");
		if (transacao.getStatus() == null) transacao.setStatus(false);
		
		return transactionDao.salvar(transacao);
	}
}
