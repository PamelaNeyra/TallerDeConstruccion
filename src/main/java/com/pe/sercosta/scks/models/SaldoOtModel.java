package com.pe.sercosta.scks.models;

import java.time.LocalDate;

public class SaldoOtModel {

	private String ot;
	private LocalDate fecha_muestreado;
	private String nombre_laboratorio;
	private double cantidad;
	
	
	public SaldoOtModel() {
		
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public LocalDate getFecha_muestreado() {
		return fecha_muestreado;
	}
	public void setFecha_muestreado(LocalDate fecha_muestreado) {
		this.fecha_muestreado = fecha_muestreado;
	}
	public String getNombre_laboratorio() {
		return nombre_laboratorio;
	}
	public void setNombre_laboratorio(String nombre_laboratorio) {
		this.nombre_laboratorio = nombre_laboratorio;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
