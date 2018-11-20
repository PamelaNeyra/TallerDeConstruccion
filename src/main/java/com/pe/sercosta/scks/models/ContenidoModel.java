package com.pe.sercosta.scks.models;

public class ContenidoModel {

	private String idLote;
	private String idPresentacion;
	private double cantidad;
	private double comprometido;
	private double saldo;
	
	public ContenidoModel() {
		
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public String getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(String idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public double getComprometido() {
		return comprometido;
	}

	public void setComprometido(double comprometido) {
		this.comprometido = comprometido;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
		
}
