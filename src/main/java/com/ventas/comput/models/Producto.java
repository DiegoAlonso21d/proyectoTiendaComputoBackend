package com.ventas.comput.models;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


@NamedQuery(name = "Producto.getAllProduct" , query = "select new com.ventas.comput.wrapper.ProductoWrapper(p.id,p.nombre,p.descripcion,p.precio,p.estado,p.categoria.id,p.categoria.nombre,p.image,p.stock) from Producto p ")

@NamedQuery(name = "Producto.updateProductStatus" , query = "update Producto p set p.estado=:estado where p.id=:id")


@NamedQuery(name = "Producto.getProductByCategory", query = "select new com.ventas.comput.wrapper.ProductoWrapper(p.id,p.nombre) from Producto p where p.categoria.id=:id and p.estado='true'")


@NamedQuery(name = "Producto.getProductById" , query = "select new com.ventas.comput.wrapper.ProductoWrapper(p.id,p.nombre,p.descripcion,p.precio,p.image,p.stock) from Producto p where p.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "productos")
public class Producto  implements Serializable{

	public static final Long serialVersionUid= 123456L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id" , nullable = false)
	private Categoria categoria;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "precio")
	private Integer precio;
	
	
	@Column(name = "stock")
	private Integer stock;
	
	
	@Column(name = "estado")
	private String estado;
	
	
	

	public Producto() {
		super();
	}














	public Producto(Integer id, String nombre, Categoria categoria, String descripcion, String image, Integer precio,
			Integer stock, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.image = image;
		this.precio = precio;
		this.stock = stock;
		this.estado = estado;
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




	public Categoria getCategoria() {
		return categoria;
	}




	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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




	public static Long getSerialversionuid() {
		return serialVersionUid;
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
