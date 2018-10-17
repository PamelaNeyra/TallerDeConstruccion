package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class MuestraModel {
	
	private String idMuestra;
	private Integer idPlanta;  
	private Integer idLaboratorio;
	private LocalDate fechaCreacion;
	private double cantidadMuestrear;
	
	public MuestraModel() {
		
	}

	public String getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(String idMuestra) {
		this.idMuestra = idMuestra;
	}

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}

	public Integer getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Integer idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaProduccion) {
		this.fechaCreacion = fechaProduccion;
	}

	public double getCantidadMuestrear() {
		return cantidadMuestrear;
	}

	public void setCantidadMuestrear(double cantidadMuestrear) {
		this.cantidadMuestrear = cantidadMuestrear;
	}
	
}
