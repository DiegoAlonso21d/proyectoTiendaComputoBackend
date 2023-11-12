package com.ventas.comput.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ventas.comput.models.Factura;

public interface FacturaDAO  extends JpaRepository<Factura, Integer>{

	List<Factura> getAllFacturas();

	List<Factura> getFacturaPorUsuario(@Param("nombres") String currentUser);


	
	
	
	
}
