package com.redreal.datajpa.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.redreal.datajpa.app.models.entity.Product;


public interface IProductoDao extends CrudRepository<Product, Long> {
	@Query("select u from Product u where u.name like %?1%")
	 List<Product> findByName(String name);
}
