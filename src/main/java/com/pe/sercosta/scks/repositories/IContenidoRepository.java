package com.pe.sercosta.scks.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Contenido;

@Repository("contenidoRepository")
public interface IContenidoRepository {
	
	/**
	 * Registra un contenido
	 * en la base de datos.
	 * @param sesion
	 * @param contenido
	 */
	public abstract void registrarContenido(Session sesion, Contenido contenido);
	
}
