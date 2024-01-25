package com.pingosystem.barriga.service.external;

import com.pingosystem.barriga.domain.Conta;

public class ContaEvent {
	
	public enum EventType {CREATED, UPDATED, DELETED}
	
	public void dispatch(Conta conta, EventType type){}
}
