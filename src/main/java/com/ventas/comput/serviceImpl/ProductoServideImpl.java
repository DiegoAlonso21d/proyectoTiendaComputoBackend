package com.ventas.comput.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.dao.ProductoDAO;
import com.ventas.comput.models.Categoria;
import com.ventas.comput.models.Producto;
import com.ventas.comput.security.jwt.JWTFilter;
import com.ventas.comput.service.ProductoService;
import com.ventas.comput.utils.ComputoUtils;
import com.ventas.comput.wrapper.ProductoWrapper;

@Service
public class ProductoServideImpl implements ProductoService{

	@Autowired
	ProductoDAO productoDao;
	
	@Autowired
	JWTFilter jwtFilter;
	
	
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
	
		try {
			
			if(jwtFilter.isAdmin()) {
				
				if(validateProductMap(requestMap,false)) {
					
					productoDao.save(getProductFromMap(requestMap,false));
					
					return ComputoUtils.getResponseEntity("Producto Agregado Correctamente", HttpStatus.OK);
					
				}
				return ComputoUtils.getResponseEntity(ComputoConstans.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
				
			}
			
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	private Producto getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
		
		Categoria categoria = new Categoria();
		
		
		
		if (requestMap.get("categoria_id") != null) {
		    categoria.setId(Integer.parseInt(requestMap.get("categoria_id")));
		}
		
		
		Producto producto= new Producto();
		
		if(isAdd) {
			
			
			producto.setId(Integer.parseInt(requestMap.get("id")));
		}else {
			
			producto.setEstado("true");
			
			
		}
		
		producto.setCategoria(categoria);
		producto.setNombre(requestMap.get("nombre"));
		producto.setDescripcion(requestMap.get("descripcion"));
		producto.setImage(requestMap.get("image"));
		producto.setPrecio(Integer.parseInt(requestMap.get("precio")));
		producto.setStock(Integer.parseInt(requestMap.get("stock")));
		return producto;
		
	
		
	}


	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
	
		
		if(requestMap.containsKey("nombre")) {
			
			if(requestMap.containsKey("id") && validateId) {
				return true;
			}
			else if (!validateId) {
				return true;
			}
			
		}
		return false;
		
	}


	@Override
	public ResponseEntity<List<ProductoWrapper>> getAllProduct() {
		
		try {
			
			return new ResponseEntity<List<ProductoWrapper>>(productoDao.getAllProduct(), HttpStatus.OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}


	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
	
		try {
			
			if(jwtFilter.isAdmin()) {
				
				if(validateProductMap(requestMap,true)) {
					
 				java.util.Optional<Producto> 	optional = productoDao.findById(Integer.parseInt(requestMap.get("id")));
 				
 				if(!optional.isEmpty()) {
 					
 					Producto producto=getProductFromMap(requestMap, true);
 					
 					producto.setEstado(optional.get().getEstado());
 					productoDao.save(producto);
 					
 					return ComputoUtils.getResponseEntity("Producto actualizado correctamente", HttpStatus.OK);
 					
 				}else {
 					return ComputoUtils.getResponseEntity("Producto no existe", HttpStatus.OK);
 				}
					
				}else {
					return ComputoUtils.getResponseEntity(ComputoConstans.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			
			if(jwtFilter.isAdmin()) {
				
				java.util.Optional<Producto> 	optional =	productoDao.findById(id);
				
				if(!optional.isEmpty()) {
					
					productoDao.deleteById(id);
					return ComputoUtils.getResponseEntity("Producto eliminado correctamente", HttpStatus.OK);
					
					
					
					
				}
				return ComputoUtils.getResponseEntity("Producto no encontrado", HttpStatus.OK);
				
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			
			if(jwtFilter.isAdmin()) {
				
				java.util.Optional<Producto> 	optional =	productoDao.findById(Integer.parseInt(requestMap.get("id")));
				
				if(!optional.isEmpty()) {
					
					productoDao.updateProductStatus(requestMap.get("estado"),Integer.parseInt(requestMap.get("id")));
					
					return ComputoUtils.getResponseEntity("Estado de producto actualizado", HttpStatus.OK);
					
				}else {
					
					return ComputoUtils.getResponseEntity("Producto no encontrado", HttpStatus.OK);
					
				}
				
				
				
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<ProductoWrapper>> getByCategory(Integer id) {
		
		try {
			
			
			return new ResponseEntity<List<ProductoWrapper>>(productoDao.getProductByCategory(id),HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	@Override
	public ResponseEntity<List<ProductoWrapper>> getProductById(Integer id) {
	
		
		try {
			
			
			return new ResponseEntity<List<ProductoWrapper>>(productoDao.getProductById(id) , HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<List<ProductoWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	
}
