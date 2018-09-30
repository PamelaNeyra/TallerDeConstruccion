package com.pe.sercosta.scks.repositories;

import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Contenido;

public interface IContenidoRepository {
	
	/**
	 * Registra un contenido
	 * en la base de datos.
	 * @param sesion
	 * @param contenido
	 */
	public abstract void registrarContenido(EntityManager sesion, Contenido contenido);
	
}
