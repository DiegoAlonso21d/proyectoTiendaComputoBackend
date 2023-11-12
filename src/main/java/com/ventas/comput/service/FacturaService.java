package com.ventas.comput.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ventas.comput.models.Factura;



public interface FacturaService {

	ResponseEntity<String> generateReport(Map<String, Object> requestMap);

	ResponseEntity<List<Factura>> getFacturas();

	ResponseEntity<byte[]> getPdf(Map<String, Object> reqMap);

	ResponseEntity<String> deleteFactura(Integer id);

	

}
