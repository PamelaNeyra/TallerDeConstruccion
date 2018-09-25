package com.pe.sercosta.scks.repositories;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Presentacion;

@Repository("presentacionRepository")
public interface IPresentacionRepository {

	/**
	 * Lista todas las presentaciones
	 * de la base de datos.
	 * @param sesion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> listarPresentacion(Session sesion);
	
	/**
	 * Lista las presentaciones que
	 * cumplan con el filtro de la
	 * presentacion.
	 * @param sesion
	 * @param presentacion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> buscarPresentacion(Session sesion, Presentacion presentacion);
	
}
