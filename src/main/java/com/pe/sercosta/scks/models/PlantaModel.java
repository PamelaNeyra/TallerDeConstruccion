package com.pe.sercosta.scks.models;

public class PlantaModel {
	
	private Integer idPlanta;
	private String nombrePlanta;
	private String ubicacionPlanta;
	
	public PlantaModel() {
		
	}
	
	public Integer getIdPlanta() {
		return idPlanta;
	}

	public void setIdPlanta(Integer idPlanta) {
		this.idPlanta = idPlanta;
	}
	
	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}
	
	public String getUbicacionPlanta() {
		return ubicacionPlanta;
	}

	public void setUbicacionPlanta(String ubicacionPlanta) {
		this.ubicacionPlanta = ubicacionPlanta;
	}
}
