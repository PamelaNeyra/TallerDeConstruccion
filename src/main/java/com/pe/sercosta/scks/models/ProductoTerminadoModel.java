package com.pe.sercosta.scks.models;

public class ProductoTerminadoModel {
	
	private String idProductoTerminado;
	private String descripcion;
	private double cantidadSinMuestrear;

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

	public double getCantidadSinMuestrear() {
		return cantidadSinMuestrear;
	}

	public void setCantidadSinMuestrear(double cantidadSinMuestrear) {
		this.cantidadSinMuestrear = cantidadSinMuestrear;
	}

}
