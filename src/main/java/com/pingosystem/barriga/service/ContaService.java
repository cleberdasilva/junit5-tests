package com.pingosystem.barriga.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pingosystem.barriga.domain.Conta;
import com.pingosystem.barriga.domain.exceptions.ValidationException;
import com.pingosystem.barriga.service.external.ContaEvent;
import com.pingosystem.barriga.service.external.ContaEvent.EventType;
import com.pingosystem.barriga.service.repositories.ContaRepository;

public class ContaService {
	private ContaRepository contaRepository;
	private ContaEvent event;

	public ContaService(ContaRepository contaRepository, ContaEvent event) {
		this.contaRepository = contaRepository;
		this.event = event;
	}
	
	public Conta salvar(Conta conta) {
		List<Conta> contas = contaRepository.obterContasPorUsuario(conta.usuario().id());
		contas.stream().forEach(contaExistente -> {
			if (conta.nome().equals(contaExistente.nome()))
				throw new ValidationException("Usuário já possui uma conta com este nome");
		});
		
		Conta contaPersistida = contaRepository.salvar(
					new Conta(conta.id(), conta.nome() + LocalDateTime.now(), conta.usuario()));
		try {
			event.dispatch(contaPersistida, EventType.CREATED);
		} catch (Exception e) {
			contaRepository.delete(contaPersistida);
			throw new RuntimeException("Falha na criação da conta, tente novamente");
		}
		return contaPersistida;
	}
}
