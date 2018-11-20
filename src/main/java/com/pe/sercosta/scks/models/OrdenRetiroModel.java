package com.pe.sercosta.scks.models;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class OrdenRetiroModel {

	private Integer idOrdenRetiro;
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaRetiro;
	private Integer idPlanta;
	private Integer idMotivo;
	private List<RetiroModel> retiroList;
	
	public OrdenRetiroModel() {
		
	}
	
	public Integer getIdOrdenRetiro() {
		return idOrdenRetiro;
	}

	public void setIdOrdenRetiro(Integer idOrdenRetiro) {
		this.idOrdenRetiro = idOrdenRetiro;
	}
	
	public LocalDate getFechaRetiro() {
		return fechaRetiro;
	}
	
	public void setFechaRetiro(LocalDate fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	
	public Integer getIdPlanta() {
		return idPlanta;
	}
	
	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}
	
	public List<RetiroModel> getRetiroList() {
		return retiroList;
	}
	
	public void setRetiroList(List<RetiroModel> retiroList) {
		this.retiroList = retiroList;
	}

	public Integer getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}	

}
