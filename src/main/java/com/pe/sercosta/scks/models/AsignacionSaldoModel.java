package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class AsignacionSaldoModel {
	private String id_Lote;
	private String id_Presentacion;
	private String id_orden_venta;
	private double cantidad;

	public AsignacionSaldoModel() {
		
	}

	public String getId_Lote() {
		return id_Lote;
	}

	public void setId_Lote(String id_Lote) {
		this.id_Lote = id_Lote;
	}

	public String getId_Presentacion() {
		return id_Presentacion;
	}

	public void setId_Presentacion(String id_Presentacion) {
		this.id_Presentacion = id_Presentacion;
	}

	public String getId_orden_venta() {
		return id_orden_venta;
	}

	public void setId_orden_venta(String id_orden_venta) {
		this.id_orden_venta = id_orden_venta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
}
