package com.ventas.comput.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

	ResponseEntity<Map<String, Object>> getCount();

	ResponseEntity<Boolean> isTokenValid(String token);
	
	
	
}
