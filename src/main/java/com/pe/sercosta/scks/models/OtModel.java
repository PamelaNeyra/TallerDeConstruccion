package com.pe.sercosta.scks.models;

import java.time.LocalDate;


public class OtModel {

	private Integer idMuestra;
	private LocalDate fechaRegistro;
	private String laboratorio;
	private String ot;
	private LocalDate fechaMuestra;
	private String estado;
	
	public OtModel() {
	
	}

	public Integer getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(Integer idMuestra) {
		this.idMuestra = idMuestra;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public LocalDate getFechaMuestra() {
		return fechaMuestra;
	}

	public void setFechaMuestra(LocalDate fechaMuestra) {
		this.fechaMuestra = fechaMuestra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
