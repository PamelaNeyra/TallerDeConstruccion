package com.pe.sercosta.scks.models;

import java.time.LocalDate;
import java.util.List;

public class MuestraModel {
	
	private String idMuestra;
	private Integer idPlanta;  
	private Integer idLaboratorio;
	private LocalDate fechaProduccion;
	private double cantidadMuestrear;
	private List<ProductoTerminadoModel> productoTerminadoList;
	
	public MuestraModel() {
		
	}

	public String getIdMuestra() {
		return idMuestra;
	}

	public void setIdMuestra(String idMuestra) {
		this.idMuestra = idMuestra;
	}

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}

	public Integer getIdLaboratorio() {
		return idLaboratorio;
	}

	public void setIdLaboratorio(Integer idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
	}

	public LocalDate getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(LocalDate fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public double getCantidadMuestrear() {
		return cantidadMuestrear;
	}

	public void setCantidadMuestrear(double cantidadMuestrear) {
		this.cantidadMuestrear = cantidadMuestrear;
	}

	public List<ProductoTerminadoModel> getProductoTerminadoList() {
		return productoTerminadoList;
	}

	public void setProductoTerminadoList(List<ProductoTerminadoModel> productoTerminadoList) {
		this.productoTerminadoList = productoTerminadoList;
	}

	
}
