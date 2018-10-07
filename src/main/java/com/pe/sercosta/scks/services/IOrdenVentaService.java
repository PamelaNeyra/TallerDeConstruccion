package com.pe.sercosta.scks.services;



import java.util.List;


import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;

public interface IOrdenVentaService {
	
	/**
	 * Lógica de negocios para
	 * - O6 - Listar Orden de Venta
	 * : Lista las ordenes de venta con filtro: planta
	 * @param planta
	 * @return List<OrdenVenta>
	 */
	public abstract List<OrdenVenta> listarOrdenVenta(Planta planta);
	
	/**
	 * Lógica de negocios para
	 * - O5 - Registrar OrdenVenta
	 * : Registra una orden de venta
	 * @param ordenVenta
	 */
	public abstract void registrarOrdenVenta(OrdenVenta ordenVenta);

}
