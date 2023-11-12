package com.ventas.comput.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ventas.comput.wrapper.ProductoWrapper;

@RequestMapping(path = "/productos")
public interface ProductoRest {

	
	@PostMapping(path = "/add")
	ResponseEntity<String> addNewProduct(@RequestBody Map<String, String> requestMap);
	
	
	@GetMapping(path = "/get")
	ResponseEntity<List<ProductoWrapper>> getAllProduct();
	
	
	@PostMapping(path = "/update")
	ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requestMap);
	
	
	@PostMapping(path = "/delete/{id}")
	ResponseEntity<String> deleteProduct(@PathVariable Integer id);
	
	
	
	@PostMapping(path = "/updateStatus")
	ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);
	
	
	@GetMapping(path = "/getByCategory/{id}")
	ResponseEntity<List<ProductoWrapper>> getByCategory(@PathVariable Integer id);
	

	@GetMapping(path = "/getById/{id}")
	ResponseEntity<List<ProductoWrapper>> getById(@PathVariable Integer id);
	
}
