package com.pe.sercosta.scks.models;

public class AsignacionModel {

	private String idLote;
	private String OT;
	private String idPresentacion;
	private String descripcion;
	private Double cantidad;
	private Double saldo; 
	

	public AsignacionModel() {
		
	}
	
	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}
	
	public String getOT() {
		return OT;
	}

	public void setOT(String OT) {
		this.OT = OT;
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
	
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
