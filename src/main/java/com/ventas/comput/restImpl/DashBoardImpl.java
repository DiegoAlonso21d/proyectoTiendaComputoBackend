package com.ventas.comput.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.comput.rest.DashboardRest;
import com.ventas.comput.service.DashboardService;


@RestController
public class DashBoardImpl implements DashboardRest{

	
	@Autowired
	DashboardService dashboardService;
	
	@Override
	public ResponseEntity<Map<String, Object>> getCount() {
	
		return dashboardService.getCount();
	}

	@Override
	public ResponseEntity<Boolean> isTokenValid(String token) {
		// TODO Auto-generated method stub
		return dashboardService.isTokenValid(token);
	}

	
	
}
