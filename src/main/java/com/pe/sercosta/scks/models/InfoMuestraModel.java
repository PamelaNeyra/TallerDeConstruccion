package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class InfoMuestraModel {
	
	private int idMuestra;
	private String ot;
	private LocalDate fechaCreacion;
	private LocalDate fechaMuestreado;
	private String nombreLaboratorio;
	private double cantidadTotal;
	private boolean estaMuestreado;
	
	public InfoMuestraModel() {
		
	}

	public int getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(int idMuestra) {
		this.idMuestra = idMuestra;
	}

	public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaMuestreado() {
		return fechaMuestreado;
	}

	public void setFechaMuestreado(LocalDate fechaMuestreado) {
		this.fechaMuestreado = fechaMuestreado;
	}

	public String getNombreLaboratorio() {
		return nombreLaboratorio;
	}

	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}

	public double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public boolean isEstaMuestreado() {
		return estaMuestreado;
	}

	public void setEstaMuestreado(boolean estaMuestreado) {
		this.estaMuestreado = estaMuestreado;
	}
	
}
