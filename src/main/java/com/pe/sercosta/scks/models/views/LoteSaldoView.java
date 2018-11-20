package com.pe.sercosta.scks.models.views;

import java.time.LocalDate;


public class LoteSaldoView {

	private String idLote;
	private LocalDate fechaProduccion;
	private double cantidadTotal;
	private double comprometidoTotal;
	private double saldo;


	
	public LoteSaldoView() {
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

	public double getCantidadTotal() {
		return cantidadTotal;
	}


	public void setCantidadTotal(double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}


	public double getComprometidoTotal() {
		return comprometidoTotal;
	}


	public void setComprometidoTotal(double comprometidoTotal) {
		this.comprometidoTotal = comprometidoTotal;
	}


	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	
	
}
