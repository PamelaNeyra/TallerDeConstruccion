package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class InfoLoteMuestreoModel {
	
	private String descripcionProdTerm;
	private String descripcion;
	private double nroBultos;
	private double nroBlocks;
	private double cantidad;
	private LocalDate fechaCaptura;
	private LocalDate fechaProduccion;
	private LocalDate fechaVencimiento;
	private String codigoTrazabilidad;
	
	public InfoLoteMuestreoModel() {
		
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

	public double getNroBultos() {
		return nroBultos;
	}

	public void setNroBultos(double nroBultos) {
		this.nroBultos = nroBultos;
	}

	public double getNroBlocks() {
		return nroBlocks;
	}

	public void setNroBlocks(double nroBlocks) {
		this.nroBlocks = nroBlocks;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDate getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(LocalDate fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
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
	
}