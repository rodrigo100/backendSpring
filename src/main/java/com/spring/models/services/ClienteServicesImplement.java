package com.spring.models.services;

import java.util.List;

//import javax.transaction.Transactional;

//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.dao.ClienteDao;
import com.spring.models.entity.Cliente;

@Service
public class ClienteServicesImplement implements ClienteServices {
    @Autowired
	private ClienteDao clienteDao;

//	@Transactional(readOnly = true)
	@Override
	public List<Cliente> allUsers() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente showCliente(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente storeCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	public void deleteCliente(Long id) {
		// TODO Auto-generated method stub
		 clienteDao.deleteById(id);
	}


}
