package com.spring.models.services;
import java.util.List;

import com.spring.models.entity.Usuario;
public interface UsuarioService {

	
	
	
	public List<Usuario> allUsers();
	public Usuario showUser(Long id);
	public Usuario storeUser(Usuario user);
	public void deleteUser(Long id);
}
