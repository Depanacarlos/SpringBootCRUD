package com.redreal.datajpa.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.redreal.datajpa.app.models.entity.Cliente;
import com.redreal.datajpa.app.models.entity.Product;

public interface IClienteService {
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable page);
	public void save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
	public List<Product> findByName(String name);
}
