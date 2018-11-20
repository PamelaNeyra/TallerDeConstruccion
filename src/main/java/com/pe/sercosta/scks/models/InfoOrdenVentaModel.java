package com.pe.sercosta.scks.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class InfoOrdenVentaModel {
	
	private String idOrdenVenta;
	private String nombreCliente;
	private String nombreLaboratorio;
	private LocalDate fechaAsignacion;
	private String certificado;
	private String nombrePlanta;
	private String paisDestino;
	private String ot;
	private LocalDate fechaEmbarque;
	private LocalTime horaEmbarque;
	private Integer aniosVencimiento;
	
	public InfoOrdenVentaModel() {
		
	}

	public String getIdOrdenVenta() {
		return idOrdenVenta;
	}

	public void setIdOrdenVenta(String idOrdenVenta) {
		this.idOrdenVenta = idOrdenVenta;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreLaboratorio() {
		return nombreLaboratorio;
	}

	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	public String getPaisDestino() {
		return paisDestino;
	}

	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}

	public String getOt() {
		return ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public LocalDate getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(LocalDate fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public LocalTime getHoraEmbarque() {
		return horaEmbarque;
	}

	public void setHoraEmbarque(LocalTime horaEmbarque) {
		this.horaEmbarque = horaEmbarque;
	}

	public Integer getAniosVencimiento() {
		return aniosVencimiento;
	}

	public void setAniosVencimiento(Integer aniosVencimiento) {
		this.aniosVencimiento = aniosVencimiento;
	}
	
}
