package com.pe.sercosta.scks.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrdenVentaModel {

	private String idOrdenVenta;
	private LocalDate fechaAsignacion ;
	private boolean estaEmbarcado;
	private String certificado;
	private LocalTime horaEmbarque;
	private LocalDate fechaEmbarque;
	private String paisDestino;
	
	private String cliente;
	private double cantidad;
	
	public OrdenVentaModel() {
		
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdOrdenVenta() {
		return idOrdenVenta;
	}

	public void setIdOrdenVenta(String idOrdenVenta) {
		this.idOrdenVenta = idOrdenVenta;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
		
	
	public Boolean getEstaEmbarcado() {
		return estaEmbarcado;
	}

	public void setEstaEmbarcado(Boolean estaEmbarcado) {
		this.estaEmbarcado = estaEmbarcado;
	}
	
	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	
	public LocalTime getHoraEmbarque() {
		return horaEmbarque;
	}

	public void setHoraEmbarque(LocalTime horaEmbarque) {
		this.horaEmbarque = horaEmbarque;
	}
	
	public LocalDate getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(LocalDate fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	
	public String getPaisDestino() {
		return paisDestino;
	}

	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}
}
