package com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import com.spring.models.dao.ClienteDao;

/*
 * importamos el modelo "Cliente" que se encuentra dentro del paquete models.entity La finalidad de llamar al modelo es que ya se tiene la estructura del modelo mapeado a la base de datos
 * con sus respectivos getters and seters, para cada campo.. esta clase representada como modelo sera utilizada
 * como un objeto de tipo lista List<Cliente>
 * */
import com.spring.models.entity.Cliente;
import com.spring.models.services.ClienteServices;

@CrossOrigin(origins= {"http://localhost:4200","http://localhost:8081"})
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	@Autowired
	private ClienteServices clienteService;

	/*Llega a consultar al repositorio */
//	 @Autowired
//		private ClienteDao clienteDao;
	
	@GetMapping("/clientes")
	public List<Cliente> index()
	{
//		return clienteService.findAll();
		return clienteService.allUsers();
		/*Llega a consultar al metodo del CrudRepository->distintos metodos, como findAll() */
//		return (List<Cliente>) clienteDao.findAll();
		
	}
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id)
	{
		return clienteService.showCliente(id);
	}
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente)
	{
		return clienteService.storeCliente(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id)
	{
		Cliente clienteActual = clienteService.showCliente(id);
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());
		
		return clienteService.storeCliente(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable Long id)
	{
		clienteService.deleteCliente(id);
	}

}
