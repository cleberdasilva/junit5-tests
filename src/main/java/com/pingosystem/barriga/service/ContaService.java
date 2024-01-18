package com.pingosystem.barriga.service;

import java.util.List;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.repositories.ContaRepository;

public class ContaService {
	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	public Conta salvar(Conta conta) {
		List<Conta> contas = contaRepository.obterContasPorUsuario(conta.usuario().id());
		contas.stream().forEach(contaExistente -> {
			if (conta.nome().equals(contaExistente.nome()))
				throw new ValidationException("Usuário já possui uma conta com este nome");
		});
		return contaRepository.salvar(conta);
	}
}
