package com.redreal.datajpa.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.redreal.datajpa.app.models.dao.IClienteDao;
import com.redreal.datajpa.app.models.dao.IProductoDao;
import com.redreal.datajpa.app.models.entity.Cliente;
import com.redreal.datajpa.app.models.entity.Product;

@Service
public class ClienteService implements IClienteService {
	
	@Autowired
	IClienteDao clienteDao;
	
	
	@Autowired
	IProductoDao productoDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {		
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return clienteDao.findAll(page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return productoDao.findByName(name);
	}

	

}
