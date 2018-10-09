package com.pe.sercosta.scks.repositories;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Muestra;

public interface IMuestraRepository {

	/**
	 * Registra una muestra
	 * en la base de datos.
	 * @param sesion
	 * @param muestra
	 */
	public abstract void registrarMuestra(EntityManager sesion, Muestra muestra);
	
}
