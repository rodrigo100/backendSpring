package com.spring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.models.entity.Usuario;
import com.spring.models.services.UsuarioService;
@CrossOrigin(origins= {"http://localhost:4200","http://localhost:8081"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
   
	@Autowired
	private UsuarioService userService;
	
	@GetMapping("/usuarios")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> index()
	{
		return userService.allUsers();
	}
	
	@GetMapping("/usuarios/{id}")
//	@ResponseStatus(HttpStatus.OK)
	
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Usuario user=null;
		Map<String, Object> response = new HashMap<>();
		try {
			user=userService.showUser(id);
		}
		catch(DataAccessException e)
		{
			response.put("Mensaje","Error al consultar la base de datos");
			response.put("Error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
		   
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(user==null)
		{
			response.put("Error","El usuario con el ID: ".concat(id.toString()).concat(" No existe en la base de datos"));		   
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
				   
		return new ResponseEntity<Usuario>(user,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/usuarios")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Usuario user)
	{
		
		Usuario usuario=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario=userService.storeUser(user);
		}
		catch(DataAccessException e)
		{
			response.put("Mensaje:","Error al registrar en la base de datos");
			response.put("Error:",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
		   
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje:","Usuario registrado ..!");
		response.put("Usuario",usuario);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	
	
	

	@PutMapping("/usuarios/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Usuario user,@PathVariable Long id)
	{
		
				Usuario usuarioActual= userService.showUser(id);
				Usuario usuarioActulizado=null;
				Map<String, Object> response = new HashMap<>();
				
				if(usuarioActual==null)
				{
					response.put("Error","El usuario con el ID: ".concat(id.toString()).concat(" No existe en la base de datos"));		   
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
				}
				try {
					
					usuarioActual.setNombre(user.getNombre());
					usuarioActual.setApellidoPaterno(user.getApellidoPaterno());
					usuarioActual.setApellidoMaterno(user.getApellidoMaterno());
					usuarioActual.setEdad(user.getEdad());
					usuarioActual.setSexo(user.getSexo());
					
					usuarioActulizado= userService.storeUser(usuarioActual);
				}
				catch(DataAccessException e)
				{
					 response.put("Mensaje:", "No se pudo realizar la actualizacion en la base de datos");
					 response.put("Error:",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
					 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
					
				}
				
				response.put("Mensaje:","Usuario Actualizado ..!");
				response.put("usuario",usuarioActulizado);
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
				
		
		
	}
	
	
	
	
	
	@DeleteMapping("/usuarios/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> destroy(@PathVariable Long id)
	{
		
		Map<String, Object> response =new HashMap<>();
		try {
			userService.deleteUser(id);
		}
		catch(DataAccessException e)
		{
			 response.put("Mensaje:", "No se pudo realizar la eliminacion en la base de datos");
			 response.put("Error:",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		response.put("Mensaje:","El usuario fue eliminado ..!");
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	
	
	
	
}
