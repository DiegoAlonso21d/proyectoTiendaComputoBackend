package com.ventas.comput.restImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.models.Factura;
import com.ventas.comput.rest.FacturaRest;
import com.ventas.comput.service.FacturaService;
import com.ventas.comput.utils.ComputoUtils;


@RestController
public class FacturaRestImpl implements FacturaRest{

	
	@Autowired
	FacturaService facturaService;
	
	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
	
		
		try {
			
			return facturaService.generateReport(requestMap);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@Override
	public ResponseEntity<List<Factura>> getFacturas() {
	
		try {
			
			return facturaService.getFacturas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map<String, Object> reqMap) {
	
		try {
			
			
			return facturaService.getPdf(reqMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	@Override
	public ResponseEntity<String> deleteFactura(Integer id) {
		
		try {
			
			return facturaService.deleteFactura(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}

	
	
	
}
