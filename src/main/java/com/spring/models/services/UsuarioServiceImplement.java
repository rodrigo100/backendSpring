package com.spring.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.dao.UsuarioDao;
import com.spring.models.entity.Usuario;
@Service
public class UsuarioServiceImplement implements UsuarioService {
    
	@Autowired
	private UsuarioDao userDao;
	
	@Override
	public List<Usuario> allUsers() {
	
		  return (List<Usuario>) userDao.findAll();
	}

	@Override
	public Usuario showUser(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElse(null);
	}

	@Override
	public Usuario storeUser(Usuario user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public void deleteUser(Long id) {
	
		  userDao.deleteById(id);
	}

}
