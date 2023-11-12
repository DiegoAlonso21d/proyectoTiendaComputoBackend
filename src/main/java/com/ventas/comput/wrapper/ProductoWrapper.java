package com.ventas.comput.wrapper;

import lombok.Data;

@Data
public class ProductoWrapper {

	Integer id;
	
	
	String nombre;
	
	String descripcion;
	
	
	Integer precio;
	Integer stock;
	
	String estado;
	String image;
	
	
	Integer categoria_id;
	
	String categoria_nombre;
	
	public ProductoWrapper() {
		
	}

	
	

	
	







public ProductoWrapper(Integer id, String nombre, String descripcion, Integer precio, String estado,
			 Integer categoria_id, String categoria_nombre,String image, Integer stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
		this.image = image;
		this.categoria_id = categoria_id;
		this.categoria_nombre = categoria_nombre;
	}













public ProductoWrapper(Integer id,String nombre) {
		
		this.id=id;
		this.nombre=nombre;
	
	}
	

public ProductoWrapper(Integer id,String nombre,String descripcion,Integer precio) {
	
	this.id=id;
	this.nombre=nombre;
	this.descripcion=descripcion;
	this.precio=precio;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getCategoria_nombre() {
		return categoria_nombre;
	}

	public void setCategoria_nombre(String categoria_nombre) {
		this.categoria_nombre = categoria_nombre;
	}






	public String getImage() {
		return image;
	}






	public void setImage(String image) {
		this.image = image;
	}













	public Integer getStock() {
		return stock;
	}













	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	
	
	
	
	
	
}
