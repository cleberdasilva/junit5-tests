package com.pingosystem.barriga.domain.builders;

import com.pingosystem.barriga.domain.Conta;
import java.lang.Long;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import com.pingosystem.barriga.domain.Transaction;


public class TransactionBuilder {
	private Transaction elemento;
	private TransactionBuilder(){}

	public static TransactionBuilder umaTransaction() {
		TransactionBuilder builder = new TransactionBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	public static void inicializarDadosPadroes(TransactionBuilder builder) {
		builder.elemento = new Transaction();
		Transaction elemento = builder.elemento;

		
		elemento.setId(1L);
		elemento.setDescricao("Transação Válida");
		elemento.setValor(10.0);
		elemento.setConta(ContaBuilder.umaConta().agora());
		elemento.setData(LocalDate.now());
		elemento.setStatus(false);
	}

	public TransactionBuilder comId(Long param) {
		elemento.setId(param);
		return this;
	}

	public TransactionBuilder comDescricao(String param) {
		elemento.setDescricao(param);
		return this;
	}

	public TransactionBuilder comValor(Double param) {
		elemento.setValor(param);
		return this;
	}

	public TransactionBuilder comConta(Conta param) {
		elemento.setConta(param);
		return this;
	}

	public TransactionBuilder comData(LocalDate param) {
		elemento.setData(param);
		return this;
	}

	public TransactionBuilder comStatus(Boolean param) {
		elemento.setStatus(param);
		return this;
	}

	public Transaction agora() {
		return elemento;
	}
}
