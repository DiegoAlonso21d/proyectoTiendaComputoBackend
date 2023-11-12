package com.ventas.comput.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.rest.UsuarioRest;
import com.ventas.comput.service.UsuarioService;
import com.ventas.comput.utils.ComputoUtils;
import com.ventas.comput.wrapper.UsuarioWrapper;


@RestController
public class UsuarioRestImpl implements UsuarioRest {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public ResponseEntity<String> register(Map<String, String> requestMap) {
		

			try {
				
				
				return usuarioService.register(requestMap);
				
			} catch (Exception e) {
		
					e.printStackTrace();
				
			}
			
		
		return	ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		
		
		try {

			return usuarioService.login(requestMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return	ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UsuarioWrapper>> getAllUser() {

		
		try {
			
			return usuarioService.getAllUser();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
return new ResponseEntity<List<UsuarioWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		
		
		try {
			
			
			return usuarioService.update(requestMap);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return	ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> checkToken() {
		
		try {
			
			
			return usuarioService.checkToken();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return	ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		
		
		try {
			
			
			return usuarioService.changePassword(requestMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
		return	ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}
