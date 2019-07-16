package com.spring.models.services;

import java.util.List;

import com.spring.models.entity.Cliente;

public interface ClienteServices {

	
	
	public List<Cliente> allClientes();
	
	public Cliente showCliente(Long id);
	public Cliente storeCliente(Cliente cliente);	
	public void deleteCliente(Long id);
}
