package com.pe.sercosta.scks.repositories;

import java.util.List;
import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.ProductoAsignacionModel;

public interface IPresentacionRepository {

	/**
	 * Lista todas las presentaciones
	 * de la base de datos.
	 * @param sesion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> listarPresentacion(EntityManager sesion);
	
	/**
	 * Lista las presentaciones que
	 * cumplan con el filtro de la
	 * presentacion.
	 * @param sesion
	 * @param presentacion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> buscarPresentacion(EntityManager sesion, Presentacion presentacion);
	
	/**
	 * Registra una Presentacion
	 * en la base de datos
	 * @param sesion
	 * @param presentacion
	 */
	public abstract void registrarPresentacion(EntityManager sesion, Presentacion presentacion);
	
	/**
	 * Actualiza una Presentacion
	 * de la base de datos 
	 * @param sesion
	 * @param presentacion
	 */
	public abstract void actualizarPresentacion(EntityManager sesion, Presentacion presentacion);
	
	/**
	 * Elimina una Presentacion
	 * de la base de datos
	 * @param sesion
	 * @param presentacion
	 */
	public abstract void eliminarPresentacion(EntityManager sesion, Presentacion presentacion);
	
	/**
	 * Lista el detalle de Presentación
	 * Asignación
	 * 
	 */
	public abstract List<ProductoAsignacionModel> listarDetalleProductoAsignacion(EntityManager sesion, Planta planta);
}
