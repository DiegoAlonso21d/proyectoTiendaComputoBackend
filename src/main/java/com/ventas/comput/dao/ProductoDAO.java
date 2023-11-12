package com.ventas.comput.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.ventas.comput.models.Producto;
import com.ventas.comput.wrapper.ProductoWrapper;

import jakarta.transaction.Transactional;

public interface ProductoDAO extends JpaRepository<Producto, Integer>{

	
	
	List<ProductoWrapper> getAllProduct();
	
	@Modifying
	@Transactional
	Integer updateProductStatus(@Param("estado") String status, @Param("id") Integer id);

	List<ProductoWrapper> getProductByCategory(@Param("id")  Integer id);

	List<ProductoWrapper> getProductById(@Param("id") Integer id);
	

	
}
