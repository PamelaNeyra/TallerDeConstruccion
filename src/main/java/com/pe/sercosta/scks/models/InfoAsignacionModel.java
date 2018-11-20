package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class InfoAsignacionModel {
	
	 private String idPresentacion;
	 private String descripcion;
	 private double nroBultos;
	 private double cantidad;
	 private LocalDate fechaProduccion;
	 private LocalDate fechaVencimiento;
	 private String codigoTrazabilidad;
	 private String nombrePlanta;
	 private String ot;
	 
	 public InfoAsignacionModel() {
		 
	 }

	public String getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(String idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getNroBultos() {
		return nroBultos;
	}

	public void setNroBultos(double nroBultos) {
		this.nroBultos = nroBultos;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(LocalDate fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodigoTrazabilidad() {
		return codigoTrazabilidad;
	}

	public void setCodigoTrazabilidad(String codigoTrazabilidad) {
		this.codigoTrazabilidad = codigoTrazabilidad;
	}

	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}
	
}
