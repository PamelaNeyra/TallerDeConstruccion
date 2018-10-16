package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;

public interface IContenidoRepository {
	
	
	/**
	 * Lista los contenidos
	 * @param sesion
	 * @return List<Contenido>
	 */
	public abstract List<Contenido> listarContenidos(EntityManager sesion);
	
	/**
	 * Registra un contenido
	 * en la base de datos.
	 * @param sesion
	 * @param contenido
	 */
	public abstract void registrarContenido(EntityManager sesion, Contenido contenido);

	/**
	 * Actualiza un contenido
	 * en la base de datos.
	 * @param sesion
	 * @param contenido
	 */
	public abstract void actualizarContenido(EntityManager sesion, Contenido contenido);
	
	/**
	 * Lista los contenidos con
	 * filtro de Presentación y
	 * Planta.
	 * @param sesion
	 * @param presentacion
	 * @return
	 */
	public abstract List<Contenido> listarContenidos(EntityManager sesion, Planta planta, Presentacion presentacion);
	
}
