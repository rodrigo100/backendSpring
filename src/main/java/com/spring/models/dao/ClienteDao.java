package com.spring.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.models.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente,Long>{

}
