package com.pe.sercosta.scks.repositories;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Muestreo;

public interface IMuestreoRepository {

	/**
	 * Registra un muestreo
	 * en la base de datos.
	 * @param sesion
	 * @param muestreo
	 */
	public abstract void registrarMuestreo(EntityManager sesion , Muestreo muestreo);
}
