package com.ventas.comput.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.dao.CategoriaDAO;
import com.ventas.comput.models.Categoria;
import com.ventas.comput.models.Producto;
import com.ventas.comput.security.jwt.JWTFilter;
import com.ventas.comput.service.CategoriaService;
import com.ventas.comput.utils.ComputoUtils;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@Autowired
	JWTFilter jwtFilter;
	
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {

	    try {

	        if (jwtFilter.isAdmin()) {

	            if (!validateCategoryMap(requestMap, false)) {
	                categoriaDAO.save(getCategoryFromMap(requestMap, true));

	                return ComputoUtils.getResponseEntity("Categoria Agregada", HttpStatus.OK);
	            }

	        } else {

	            throw new Exception("El usuario no tiene la autoridad para agregar una nueva categoría");

	        }

	        return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	    } catch (Unauthorized e) {
	        // Handle unauthorized exception
	        return ComputoUtils.getResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
	    } catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();

	    }

	    return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {

		
			if(requestMap.containsKey("nombre")) {
				
				if(requestMap.containsKey("id") && validateId) {
					return true;
				}else if (!validateId) {
					return false;
				}
				
			}
		return false;
		
	}

	
	
	private Categoria getCategoryFromMap(Map<String,String> requestMap, Boolean isAdd) {
		
		Categoria categoria = new Categoria();
		
		if(requestMap.get("id") != null && isAdd) {
			categoria.setId(Integer.parseInt(requestMap.get("id")));
			
		}
		
		categoria.setNombre(requestMap.get("nombre"));
		
		
		
		
		return categoria;
		
		
		
	}

	@Override
	public ResponseEntity<List<Categoria>> getAllCategory(String filterValue) {
	
		try {
		
			if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				
				return new  ResponseEntity<List<Categoria>>(categoriaDAO.getAllCategory(),HttpStatus.OK);
				
				
				
			}else {
				return new ResponseEntity<List<Categoria>>(categoriaDAO.findAll(),HttpStatus.OK);
			}
			
			
			
			
		} catch (Exception e) {
		      e.printStackTrace();
		}
		return new ResponseEntity<List<Categoria>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCaregory(Map<String, String> requestMap) {
		
		
	try {
		
		if(jwtFilter.isAdmin()) {
			
			if(validateCategoryMap(requestMap, true))
			{
				java.util.Optional<Categoria> optional= categoriaDAO.findById(Integer.parseInt(requestMap.get("id")));
				
				if(!optional.isEmpty()) {
					
					categoriaDAO.save(getCategoryFromMap(requestMap, true));
					
					   return ComputoUtils.getResponseEntity("Categoria actualizada correctamente", HttpStatus.OK);
					
				}else {
					   return ComputoUtils.getResponseEntity("Categoria id no existe", HttpStatus.OK);
				}
				
				
			}
			   return ComputoUtils.getResponseEntity("Categoria id no existe", HttpStatus.OK);
			
		}else {
		    return ComputoUtils.getResponseEntity(ComputoConstans.INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
		
	} catch (Exception e) {
	      e.printStackTrace();
	}
	
    return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	
	
	
	
	}

	@Override
	public ResponseEntity<String> deleteCategory(Integer id) {
		try {
			
			if(jwtFilter.isAdmin()) {
				
				java.util.Optional<Categoria> 	optional =	categoriaDAO.findById(id);
				
				if(!optional.isEmpty()) {
					
					categoriaDAO.deleteById(id);
					return ComputoUtils.getResponseEntity("Categoria eliminado correctamente", HttpStatus.OK);
					
					
					
					
				}
				return ComputoUtils.getResponseEntity("Categoriano encontrado", HttpStatus.OK);
				
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
