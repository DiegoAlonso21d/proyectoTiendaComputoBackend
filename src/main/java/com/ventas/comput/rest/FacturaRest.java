package com.ventas.comput.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ventas.comput.models.Factura;

@RequestMapping(path = "/facturas")
public interface FacturaRest {

	
	@PostMapping(path = "/generateReport")
	ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap );
	
	
	@GetMapping(path = "/getFacturas")
	ResponseEntity<List<Factura>> getFacturas();
	
	
	@PostMapping(path = "/getPdf")
	ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> reqMap);
	
	
	@PostMapping(path = "/delete/{id}")
	ResponseEntity<String> deleteFactura(@PathVariable Integer id);
	
	
	
	
}
