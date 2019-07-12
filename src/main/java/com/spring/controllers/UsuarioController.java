package com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@ResponseStatus(HttpStatus.OK)
	public Usuario show(@PathVariable Long id)
	{
		return userService.showUser(id);
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario user)
	{
		
		return userService.storeUser(user);	
	}
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario user,@PathVariable Long id)
	{
		Usuario userRequired = userService.showUser(id);
		userRequired.setNombre(user.getNombre());
		userRequired.setApellidoPaterno(user.getApellidoPaterno());
		userRequired.setApellidoMaterno(user.getApellidoMaterno());
		userRequired.setSexo(user.getSexo());
		
		return userService.storeUser(userRequired);
	}
	
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void destroy(@PathVariable Long id)
	{
		userService.deleteUser(id);
	}
	
	
	
	
	
}
