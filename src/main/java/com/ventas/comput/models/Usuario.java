package com.ventas.comput.models;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


@NamedQuery(name = "Usuario.findByEmailId",query = "select u from Usuario u where u.email=:email")

@NamedQuery(name = "Usuario.getAllUser",query = "select new com.ventas.comput.wrapper.UsuarioWrapper(u.id,u.nombres,u.email,u.telefono,u.estado) from Usuario u where u.role='usuario'")

@NamedQuery(name = "Usuario.updateStatus",query = "update Usuario u set u.estado=:estado where u.id=:id")

@NamedQuery(name = "Usuario.getAllAdmin",query = "select u.email from Usuario u where u.role='administrador'")





@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "usuarios")
public class Usuario implements Serializable {

	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombres")
	private String nombres;
	
	
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

	
	public Usuario()
	{
		
	}
	
	public Usuario(String nombre, String telefono, String email, String password, String estado, String role) {
		super();
		this.nombres = nombre;
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
		return nombres;
	}

	public void setNombre(String nombre) {
		this.nombres = nombre;
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
	
	

	
	
	
	
}
