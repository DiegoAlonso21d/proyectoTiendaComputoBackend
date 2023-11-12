package com.ventas.comput.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ventas.comput.models.Categoria;

public interface CategoriaService {

	ResponseEntity<String>  addNewCategory (Map<String,String> requestMap);
	
	ResponseEntity<List<Categoria>>  getAllCategory(String filterValue);
	
	ResponseEntity<String>  updateCaregory (Map<String,String> requestMap);

	ResponseEntity<String> deleteCategory(Integer id);
	
	
}
