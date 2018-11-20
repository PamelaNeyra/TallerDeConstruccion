package com.pe.sercosta.scks.repositories;

import javax.persistence.EntityManager;


import com.pe.sercosta.scks.entities.Retiro;

public interface IRetiroRepository {
	
	
	/**
	 * Registra un retiro
	 * en la base de datos.
	 * @param sesion
	 * @param retiro
	 */
	public abstract void registrarRetiro(EntityManager sesion, Retiro retiro);
	
	
}
