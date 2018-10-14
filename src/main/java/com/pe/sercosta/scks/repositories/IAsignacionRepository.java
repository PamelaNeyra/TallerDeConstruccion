package com.pe.sercosta.scks.repositories;


import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.models.AsignacionModel;


public interface IAsignacionRepository {
	

	/**
	 * Lista la asignacion que
	 * cumplan con el filtro de la
	 * planta e id de orden de venta.
	 * @param sesion
	 * @param asignacion
	 * @param planta
	 * @return List<Asignacion>
	 */
	public abstract List<AsignacionModel> listarAsignacion(EntityManager sesion, OrdenVenta orden);
	
	/**
	 * Registra una asignacion
	 * en la base de datos.
	 * @param sesion
	 * @param asignacion
	 */
	public abstract void registrarAsignacion(EntityManager sesion, Asignacion asignacion);

}
