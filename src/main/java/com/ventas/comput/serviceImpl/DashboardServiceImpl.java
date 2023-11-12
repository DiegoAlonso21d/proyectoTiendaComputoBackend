package com.ventas.comput.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ventas.comput.dao.CategoriaDAO;
import com.ventas.comput.dao.FacturaDAO;
import com.ventas.comput.dao.ProductoDAO;
import com.ventas.comput.service.DashboardService;

import io.jsonwebtoken.Claims;

@Service
public class DashboardServiceImpl  implements DashboardService{

	
	@Autowired
	CategoriaDAO categoriaDao;
	
	@Autowired
	ProductoDAO productoDao;
	
	@Autowired
	FacturaDAO facturaDao;
	
	
	@Override
	public ResponseEntity<Map<String, Object>> getCount() {
	
		Map<String, Object> map  =  new HashMap<>();
		
		map.put("categoria", categoriaDao.count());
		
		map.put("producto", productoDao.count());
		
		map.put("factura", facturaDao.count());
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		
		
		
	}


	@Override
	public ResponseEntity<Boolean> isTokenValid(String token) {
		
		
		
		   boolean isValid = isValidToken(token);
	        
	        if (isValid) {
	            return ResponseEntity.ok(true);
	        } else {
	            return ResponseEntity.ok(false);
	        }
	        
	        
	        
	        
	        
	    }
	


public boolean isValidToken(String token) {

	
	
    return token != null && !token.isEmpty();
    
    
    
    
}


	

	
	
	
	
	
	
	
	
}
