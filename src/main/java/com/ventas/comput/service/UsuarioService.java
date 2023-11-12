package com.ventas.comput.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ventas.comput.wrapper.UsuarioWrapper;

public interface UsuarioService {

	
	ResponseEntity<String> register(Map<String,String> requestMap);
	
	ResponseEntity<String> login(Map<String,String> requestMap);
	
	ResponseEntity<List<UsuarioWrapper>> getAllUser();
	
	ResponseEntity<String> update(Map<String,String> requestMap);
	
	ResponseEntity<String> checkToken();
	
	ResponseEntity<String> changePassword(Map<String,String> requestMap);
	
	
}
