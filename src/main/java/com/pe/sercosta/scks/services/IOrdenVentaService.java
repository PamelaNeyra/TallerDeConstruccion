package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;

public interface IOrdenVentaService {
	
	/**
	 * Lógica de negocios para
	 * - O6 - Listar Orden de Venta
	 * : Lista las ordenes de venta con filtro: planta
	 * @return List<OrdenVenta>
	 */
	public abstract List<OrdenVenta> listarOrdenVenta(Planta planta);
	
	/**
	 * Lógica de negocios para
	 * - 06 - Obtener Orden de Venta
	 * : Obtiene una orden de venta con filtro: orden
	 * @param orden
	 * @return
	 */
	public abstract OrdenVenta obtenerOrdenVenta(OrdenVenta orden);
		
	/**
	 * Lógica de negocios para
	 * - O5 - Registrar OrdenVenta
	 * : Registra una orden de venta
	 * @param ordenVenta
	 */
	public abstract void registrarOrdenVenta(OrdenVenta ordenVenta);
	
	/**
	 * Lógica de negocios para
	 * - O5 - Buscar OrdenVenta
	 * : Buscar una orden de venta
	 * @param ordenVenta
	 */
	public abstract List<OrdenVenta> buscarOrdenVenta(OrdenVenta ordenVenta);
	
	/**
	 * Lógica de negocios para
	 * - O5 - Actualizar OrdenVenta
	 * : Actualizar una orden de venta
	 * @param ordenVenta
	 */
	public abstract void actualizarOrdenVenta(OrdenVenta ordenVenta);

}
