package com.pingosystem.barriga.service.repositories;

import java.util.List;

import com.pingosystem.barriga.domain.Conta;

public interface ContaRepository {

	Conta salvar(Conta conta);
	
	List<Conta> obterContasPorUsuario(Long usuarioId);
	
	void delete(Conta conta);
}
