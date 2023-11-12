package com.ventas.comput.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.rest.ProductoRest;
import com.ventas.comput.service.ProductoService;
import com.ventas.comput.utils.ComputoUtils;
import com.ventas.comput.wrapper.ProductoWrapper;


@RestController
public class ProductoRestImpl implements ProductoRest{

	@Autowired
	ProductoService productoService;
	
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {


		try {
			
			
			return productoService.addNewProduct(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProductoWrapper>> getAllProduct() {
		
		try {
			
			
			return productoService.getAllProduct();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
		
		try {
			
			
			return productoService.updateProduct(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		
		
		try {
			return productoService.deleteProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		
		
		try {
			
			return productoService.updateStatus(requestMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProductoWrapper>> getByCategory(Integer id) {
		
		try {
			
			return productoService.getByCategory(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<List<ProductoWrapper>> getById(Integer id) {
		try {
			
			return productoService.getProductById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	

}
