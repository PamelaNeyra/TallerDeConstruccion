package com.pe.sercosta.scks.models;

public class ProductoTerminadoModel {
	
	private String idProductoTerminado;
	private String descripcion;
	private Double cantidadTotal;

	public ProductoTerminadoModel() {
		
	}

	public String getIdProductoTerminado() {
		return idProductoTerminado;
	}

	public void setIdProductoTerminado(String idProductoTerminado) {
		this.idProductoTerminado = idProductoTerminado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

}
