package com.pingosystem.barriga.service;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.service.repositories.ContaRepository;

public class ContaService {
	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	public Conta salvar(Conta conta) {
		return contaRepository.salvar(conta);
	}
}
