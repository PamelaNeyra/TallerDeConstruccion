package com.pe.sercosta.scks.models.views;

public class PresentacionView {
	
	private String idPresentacion;
	private String descripcion;
	private Double comprometidoTotal;
	private Double cantidadTotal;
	
	public PresentacionView() {
		
	}

	public String getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(String idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getComprometidoTotal() {
		return comprometidoTotal;
	}

	public void setComprometidoTotal(Double comprometidoTotal) {
		this.comprometidoTotal = comprometidoTotal;
	}

	public Double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

}
