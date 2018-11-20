package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class OrdenVentaClienteModel {
	private String id_ordenVenta;
	private String nombreCliente;
	private LocalDate fechaAsignacion; 
	private LocalDate fechaEmbarque;
	private double cantidadTotal;
	private boolean estaEmbarcado;
	
	public void OrdenVentaCliente() {
		
	}

	public String getId_ordenVenta() {
		return id_ordenVenta;
	}

	public void setId_ordenVenta(String id_ordenVenta) {
		this.id_ordenVenta = id_ordenVenta;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public LocalDate getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(LocalDate fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public boolean isEstaEmbarcado() {
		return estaEmbarcado;
	}

	public void setEstaEmbarcado(boolean estaEmbarcado) {
		this.estaEmbarcado = estaEmbarcado;
	}
	
	
	
}
