package com.ventas.comput.rest;









import java.util.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ventas.comput.wrapper.UsuarioWrapper;



@RequestMapping(path = "/usuarios")
public interface UsuarioRest {

	
	@PostMapping(path = "/register" )
	public ResponseEntity<String> register(@RequestBody(required = true) Map<String,String> requestMap);
	
	
	@PostMapping(path = "/login" )
	public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap);
	
	
	@GetMapping(path = "/get")
	public ResponseEntity<List<UsuarioWrapper>> getAllUser();
	
	
	
	@PostMapping(path = "/update")
	public ResponseEntity<String> update(@RequestBody(required = true) Map<String,String> requestMap);
	
	
	@GetMapping(path = "/checkToken")
	public ResponseEntity<String> checkToken();
	
	
	@PostMapping(path = "/changePassword")
	ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap);
	

	
	
	
	
}
