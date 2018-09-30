package com.pe.sercosta.scks.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Presentacion;

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
	
}
