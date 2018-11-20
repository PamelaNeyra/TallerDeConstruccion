package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class AsignacionSaldoPresentacionModel {
	private String idOrdenVenta;
	public String nombreCliente;
	private String idLote;
	private String ot;
	private double cantidad;
	private LocalDate fechaAsignacion;
	

	public AsignacionSaldoPresentacionModel() {
		
	}


	public String getIdOrdenVenta() {
		return idOrdenVenta;
	}


	public void setIdOrdenVenta(String idOrdenVenta) {
		this.idOrdenVenta = idOrdenVenta;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public String getIdLote() {
		return idLote;
	}


	public void setIdLote(String idLote) {
		this.idLote = idLote;
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


	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}


	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	
	

	
}
