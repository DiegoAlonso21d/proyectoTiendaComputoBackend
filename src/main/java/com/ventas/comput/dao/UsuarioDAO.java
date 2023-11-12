package com.ventas.comput.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.comput.models.Usuario;
import com.ventas.comput.wrapper.UsuarioWrapper;

import jakarta.transaction.Transactional;


@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{

	Usuario findByEmailId(@Param("email") String email);
	
	List<UsuarioWrapper> getAllUser();
	
	List<String> getAllAdmin();
	
	@Transactional
	@Modifying
	Integer updateStatus(@Param("estado") String estado, @Param("id") Integer id);
	
	
	
	Usuario findByEmail(String email);
	
	
	
}
