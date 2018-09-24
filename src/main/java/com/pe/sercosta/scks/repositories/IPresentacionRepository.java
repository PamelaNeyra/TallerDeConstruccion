package com.pe.sercosta.scks.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Presentacion;

@Repository("presentacionRepository")
public interface IPresentacionRepository{

	/**
	 * Lista todas las presentaciones
	 * de la base de datos.
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> listarPresentacion();
	
	/**
	 * Lista las presentaciones que
	 * cumplan con el filtro de la
	 * presentacion.
	 * @param presentacion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> buscarPresentacion(Presentacion presentacion);
	
}
