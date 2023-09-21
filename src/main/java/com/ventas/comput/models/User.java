package com.ventas.comput.models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User {

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombres")
	private String nombre;
	
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="role")
	private String role;
	
	

	/**
	public User() {
		
	}
	
	public User(String nombre, String telefono, String email, String password, String estado, String role) {
	
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.estado = estado;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
	
		*/
	
	
	
	
	
}
