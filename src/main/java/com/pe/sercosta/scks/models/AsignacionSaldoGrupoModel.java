package com.pe.sercosta.scks.models;

public class AsignacionSaldoGrupoModel {
	private String id_orden_venta;
	private double cantidad;
	
	public AsignacionSaldoGrupoModel(){
		
	}

	public String getId_orden_venta() {
		return id_orden_venta;
	}

	public void setId_orden_venta(String id_orden_venta) {
		this.id_orden_venta = id_orden_venta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
