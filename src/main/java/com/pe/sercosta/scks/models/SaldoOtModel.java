package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class SaldoOtModel {

	private String ot;
	private LocalDate fechaMuestreado;
	private String nombreLaboratorio;
	private double cantidad;
	
	
	public SaldoOtModel() {
		
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
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
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
