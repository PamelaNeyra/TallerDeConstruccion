package com.pe.sercosta.scks.models.views;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LoteView {
	private String idLote;
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaProduccion;
	
	public LoteView() {
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
}
