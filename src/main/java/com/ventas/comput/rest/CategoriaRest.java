package com.ventas.comput.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ventas.comput.models.Categoria;

@RequestMapping(path = "/categorias")
public interface CategoriaRest {

	
	@PostMapping(path = "/add")
	ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String,String> requestMap);
	
	
	
	@GetMapping(path = "/get")
	ResponseEntity<List<Categoria>> getAllCategory(@RequestParam(required = false) String filterValue);
	
	
	@PostMapping(path = "/update")
	ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String,String> requestMap);
	
	
	@PostMapping(path = "/delete/{id}")
	ResponseEntity<String> deleteCategory(@PathVariable Integer id);
	
	
}
