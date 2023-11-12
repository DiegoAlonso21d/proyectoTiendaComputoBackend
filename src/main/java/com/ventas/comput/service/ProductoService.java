package com.ventas.comput.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ventas.comput.wrapper.ProductoWrapper;

public interface ProductoService {

	
	ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
	
	ResponseEntity<List<ProductoWrapper>> getAllProduct();

	ResponseEntity<String> updateProduct(Map<String, String> requestMap);

	ResponseEntity<String> deleteProduct(Integer id);

	ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	ResponseEntity<List<ProductoWrapper>> getByCategory(Integer id);

	ResponseEntity<List<ProductoWrapper>> getProductById(Integer id);
	
	
	
	
}
