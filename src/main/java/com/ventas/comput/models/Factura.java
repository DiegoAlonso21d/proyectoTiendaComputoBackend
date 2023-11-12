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



@NamedQuery(name = "Factura.getAllFacturas" , query = "select f from Factura f order by f.id desc")


@NamedQuery(name = "Factura.getFacturaPorUsuario" ,query = "select f from Factura f where f.create=:nombres order by f.id desc")



@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "facturas")
public class Factura implements Serializable {

		private static final long serialVersionUID= 1L;
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
	
		@Column(name = "uuid")
		private String uuid;
		
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "email")
		private String email;
		
		
		@Column(name = "telefono")
		private String telefono;
		
		
		@Column(name = "metodoPago")
		private String metodoPago;
		
		@Column(name = "total")
		private Integer total;
		
		
		@Column(name = "detalleProducto",columnDefinition = "json")
		private String detalleProducto;
	
		
		@Column(name = "createdBy")
		private String create;


		public Factura(Integer id, String uuid, String nombre, String email, String telefono, String metodoPago,
				Integer total, String detalleProducto, String create) {
			super();
			this.id = id;
			this.uuid = uuid;
			this.nombre = nombre;
			this.email = email;
			this.telefono = telefono;
			this.metodoPago = metodoPago;
			this.total = total;
			this.detalleProducto = detalleProducto;
			this.create = create;
		}


		public Factura() {
			super();
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getUuid() {
			return uuid;
		}


		public void setUuid(String uuid) {
			this.uuid = uuid;
		}


		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
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


		public String getMetodoPago() {
			return metodoPago;
		}


		public void setMetodoPago(String metodoPago) {
			this.metodoPago = metodoPago;
		}


		public Integer getTotal() {
			return total;
		}


		public void setTotal(Integer total) {
			this.total = total;
		}


		public String getDetalleProducto() {
			return detalleProducto;
		}


		public void setDetalleProducto(String detalleProducto) {
			this.detalleProducto = detalleProducto;
		}


		public String getCreate() {
			return create;
		}


		public void setCreate(String create) {
			this.create = create;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
