package com.ventas.comput.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "/dashboard")
public interface DashboardRest  {

	
	@GetMapping(path = "/details")
	ResponseEntity<Map<String, Object>> getCount();
		
	
    @GetMapping(path= "/isTokenValid")
    ResponseEntity<Boolean> isTokenValid(@RequestParam("x-token") String token) ;
 

	
	
}
