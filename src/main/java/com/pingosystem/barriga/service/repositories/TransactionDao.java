package com.pingosystem.barriga.service.repositories;

import com.pingosystem.barriga.domain.Transaction;

public interface TransactionDao {

	Transaction salvar(Transaction transacao);
}
