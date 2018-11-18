package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class ProductoAsignacionModel {
	private String id_ordenventa;
	private String nombre_cliente;
	private String id_lote;
	private String ot;
	private double cantidad;
	private LocalDate fecha_asignacion;
	
	public void ProductoAsignacionModel() {
		
	}

	public String getId_ordenventa() {
		return id_ordenventa;
	}

	public void setId_ordenventa(String id_ordenventa) {
		this.id_ordenventa = id_ordenventa;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getId_lote() {
		return id_lote;
	}

	public void setId_lote(String id_lote) {
		this.id_lote = id_lote;
	}

	public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFecha_asignacion() {
		return fecha_asignacion;
	}

	public void setFecha_asignacion(LocalDate fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}
	
	
}
