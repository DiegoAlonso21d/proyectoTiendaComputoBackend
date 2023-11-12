package com.ventas.comput.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.models.Categoria;
import com.ventas.comput.rest.CategoriaRest;
import com.ventas.comput.service.CategoriaService;
import com.ventas.comput.utils.ComputoUtils;

@RestController
public class CategoriaRestImpl implements CategoriaRest{

	
	@Autowired
	CategoriaService categoriaService;
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
	
		
		try {
			
			return categoriaService.addNewCategory(requestMap);
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}

	@Override
	public ResponseEntity<List<Categoria>> getAllCategory(String filterValue) {
		
		try {
			
			
			return categoriaService.getAllCategory(filterValue);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Categoria>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		
		

		
		try {
			return categoriaService.updateCaregory(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Integer id) {
		
		try {
			return categoriaService.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
