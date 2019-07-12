package com.spring.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.models.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {

}
