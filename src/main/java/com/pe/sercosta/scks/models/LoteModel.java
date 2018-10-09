package com.pe.sercosta.scks.models;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LoteModel {

	private String idLote;
	private Integer idPlanta;
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaProduccion;
	private double cantidadRecepcion;
	private List<ContenidoModel> contenidoList;
	
	public LoteModel() {
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public LocalDate getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(LocalDate fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public double getCantidadRecepcion() {
		return cantidadRecepcion;
	}

	public void setCantidadRecepcion(double cantidadRecepcion) {
		this.cantidadRecepcion = cantidadRecepcion;
	}

	public List<ContenidoModel> getContenidoList() {
		return contenidoList;
	}

	public void setContenidoList(List<ContenidoModel> contenidoList) {
		this.contenidoList = contenidoList;
	}

	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}
	
}
