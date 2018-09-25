package com.pe.sercosta.scks.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Presentacion;

@Service("presentacionService")
public interface IPresentacionService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista las presentaciones
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> listarPresentacion();
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Lista las presentaciones con filtro
	 * @param presentacion
	 * @return List<Presentacion>
	 */
	public abstract List<Presentacion> buscarPresentacion(Presentacion presentacion);

}
