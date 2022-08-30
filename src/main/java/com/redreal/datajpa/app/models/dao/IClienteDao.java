package com.redreal.datajpa.app.models.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.redreal.datajpa.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {
	
}
