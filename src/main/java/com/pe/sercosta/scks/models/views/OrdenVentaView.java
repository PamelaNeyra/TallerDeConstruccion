package com.pe.sercosta.scks.models.views;

import java.time.LocalDate;

public class OrdenVentaView {

	private String idOrdenVenta;
	private LocalDate fechaAsignacion;
	private Integer idPlanta;
	private Integer idCliente;
	private String paisDestino;	
	private String certificado;
	
	public OrdenVentaView() {
		
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

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getPaisDestino() {
		return paisDestino;
	}

	public void setPaisDestino(String paisDestino) {
		this.paisDestino = paisDestino;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
}
