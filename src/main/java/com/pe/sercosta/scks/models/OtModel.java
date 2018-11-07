package com.pe.sercosta.scks.models;

import java.time.LocalDate;


public class OtModel {

	private Integer idMuestra;
	private LocalDate fechaCreacion;
	private String idLaboratorio;
	private String ot;
	private LocalDate fechaMuestreado;
	private String estado;
	private Double cantidadTotal;
	
	public Double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public OtModel() {
	
	}

	public Integer getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(Integer idMuestra) {
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

	public String getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(String idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public LocalDate getFechaMuestreado() {
		return fechaMuestreado;
	}

	public void setFechaMuestreado(LocalDate fechaMuestreado) {
		this.fechaMuestreado = fechaMuestreado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
