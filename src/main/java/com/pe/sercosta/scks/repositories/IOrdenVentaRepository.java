package com.pe.sercosta.scks.repositories;


import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.views.OrdenVentaView;


public interface IOrdenVentaRepository {
	
	/**
	 * Lista todas las ordenes de venta
	 * de la base de datos segun planta.
	 * @param sesion
	 * @param planta
	 * @return List<OrdenVenta>
	 */
	public abstract List<OrdenVentaView> listarOrdenVenta(EntityManager sesion,Planta planta);
	
	public abstract OrdenVentaModel obtenerOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta);
	
	/**
	 * Lista una orden de venta
	 * de la base de datos segun planta e id de la orden de venta.
	 * @param sesion
	 * @param ordenVenta
	 * @return List<OrdenVenta>
	 */
	public abstract List<OrdenVenta> buscarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta);
	
	/**
	 * Actualiza una orden de venta
	 * de la base de datos segun planta e id de la orden de venta.
	 * @param sesion
	 * @param ordenVenta
	 * @param planta
	 */
	public abstract void actualizarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta);
	
	
	/**
	 * Registra una orden de venta
	 * en la base de datos.
	 * @param sesion
	 * @param ordenVenta
	 */
	public abstract void registrarOrdenVenta(EntityManager sesion, OrdenVenta ordenVenta);

}
