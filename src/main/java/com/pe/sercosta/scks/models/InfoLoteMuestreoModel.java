package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class InfoLoteMuestreoModel {
	private String descripcionProdTerm;
	private String descripcion;
	private double bloque;
	private LocalDate fechaCaptura;
	private LocalDate fechaCreacion;
	private String codigoTrazabilidad;
	
	public void InfoLoteMuestreoModel() {
		
	}

	public String getDescripcionProdTerm() {
		return descripcionProdTerm;
	}

	public void setDescripcionProdTerm(String descripcionProdTerm) {
		this.descripcionProdTerm = descripcionProdTerm;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getBloque() {
		return bloque;
	}

	public void setBloque(double bloque) {
		this.bloque = bloque;
	}

	public LocalDate getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(LocalDate fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCodigoTrazabilidad() {
		return codigoTrazabilidad;
	}

	public void setCodigoTrazabilidad(String codigoTrazabilidad) {
		this.codigoTrazabilidad = codigoTrazabilidad;
	}
	
	

	
}
