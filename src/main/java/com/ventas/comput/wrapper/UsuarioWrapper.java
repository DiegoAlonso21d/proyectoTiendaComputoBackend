package com.ventas.comput.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioWrapper {


	
	private Integer id;
	
	private String nombres;
	
	private String  email;
	
	private String telefono;
	
	private String estado;
	
	private String role;
	
	
	public UsuarioWrapper() {
		
	}
	
	

	public UsuarioWrapper(Integer id, String nombres, String email, String telefono, String estado,String role) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.email = email;
		this.telefono = telefono;
		this.estado = estado;
		this.role=role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
}
