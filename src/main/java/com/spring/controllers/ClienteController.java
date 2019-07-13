package com.spring.controllers;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.spring.models.dao.ClienteDao;

/*
 * importamos el modelo "Cliente" que se encuentra dentro del paquete models.entity La finalidad de llamar al modelo es que ya se tiene la estructura del modelo mapeado a la base de datos
 * con sus respectivos getters and seters, para cada campo.. esta clase representada como modelo sera utilizada
 * como un objeto de tipo lista List<Cliente>
 * */
import com.spring.models.entity.Cliente;
import com.spring.models.services.ClienteServices;


/*probando la clase si sube los cambios a Git*/
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
	public ResponseEntity<?> show(@PathVariable Long id)
	{
	   
		Cliente cliente = null;

		Map<String, Object> response = new HashMap<>();

		try {

		   cliente  = clienteService.showCliente(id);

		} catch(DataAccessException e){

		response.put("mensaje", "!Error al realizar la consulta en la base de datos!");

		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {

		response.put("mensaje", "El cliente con ID:".concat(id.toString().concat(" No se encuentra en la base de datos")));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND );

		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);


		
	}
	
	
	
	@PostMapping("/clientes")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Cliente cliente)
	{
		Cliente clientNew =null;
		Map<String, Object> response = new HashMap<>();
		try
		{
			clientNew = clienteService.storeCliente(cliente); 
		}
		catch(DataAccessException e)
		{
			 response.put("error", "No se pudo realizar el registro en la base de datos");
			 response.put("mensaje",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		 response.put("mensaje", "Cliente registrado ..!");
		 response.put("cliente",clientNew);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	
	 
	@PutMapping("/clientes/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id)
	{
		Cliente clienteActual=  clienteService.showCliente(id);
		Map<String, Object> response = new HashMap<>();
		Cliente clienteActualizado=null;
		
		if(clienteActual==null)
		{
			response.put("error", "El cliente con ID:".concat(id.toString().concat(" No se encuentra en la base de datos")));
			 return new ResponseEntity<	Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		try
		{
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			
			clienteActualizado =clienteService.storeCliente(clienteActual);
			
		}
		catch(DataAccessException e)
		{
			 response.put("error", "No se pudo realizar la actualizacion en la base de datos");
			 response.put("mensaje",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		 response.put("mensaje", "Cliente actualizado ..!");
		 response.put("cliente",clienteActualizado);
		
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	 	
		
	}
	
	@DeleteMapping("/clientes/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?>  destroy(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			clienteService.deleteCliente(id);
		}
		catch(DataAccessException e)
		{	
			 response.put("error", "No se pudo realizar la eliminacion en la base de datos");
			 response.put("mensaje",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
			
		}
		 response.put("mensaje", "Cliente eliminado ..!");
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	
		
	}

}
