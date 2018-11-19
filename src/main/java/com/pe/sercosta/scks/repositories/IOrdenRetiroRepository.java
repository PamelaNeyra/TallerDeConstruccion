package com.pe.sercosta.scks.repositories;

import javax.persistence.EntityManager;


import com.pe.sercosta.scks.entities.OrdenRetiro;

public interface IOrdenRetiroRepository {
	
	
	/**
	 * Registra una orden de retiro
	 * en la base de datos.
	 * @param sesion
	 * @param ordenRetiro
	 */
	public abstract void registrarOrdenRetiro(EntityManager sesion, OrdenRetiro ordenRetiro);
	
	
}
