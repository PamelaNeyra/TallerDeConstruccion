package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class InfoAsignacionModel {
	 private String id_presentacion;
	 private String descripcion;
	 private double bloque;
	 private LocalDate fechaAsignacion;
	 private String codigoTrazabilidad;
	 private String nombrePlanta;
	 private String ot;
	 
	 public void InfoAsignacionModel() {
		 
	 }
	
	public String getId_presentacion() {
		return id_presentacion;
	}
	
	public void setId_presentacion(String id_presentacion) {
		this.id_presentacion = id_presentacion;
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
	
	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}
	
	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
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
