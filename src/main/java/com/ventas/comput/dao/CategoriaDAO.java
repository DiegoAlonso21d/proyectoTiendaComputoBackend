package com.ventas.comput.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ventas.comput.models.Categoria;
import com.ventas.comput.wrapper.ProductoWrapper;


public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{

	
	
	List<Categoria> getAllCategory();
	
	
	List<ProductoWrapper> getCategoryById(@Param("id") Integer id);
	
	
	
}
