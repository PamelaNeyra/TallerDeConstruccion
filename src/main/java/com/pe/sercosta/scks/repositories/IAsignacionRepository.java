package com.pe.sercosta.scks.repositories;


import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Planta;


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
	public abstract List<Asignacion> buscarAsignacion(EntityManager sesion, Asignacion asignacion, Planta planta);
	
	/**
	 * Registra una asignacion
	 * en la base de datos.
	 * @param sesion
	 * @param asignacion
	 */
	public abstract void registrarAsignacion(EntityManager sesion, Asignacion asignacion);

}
