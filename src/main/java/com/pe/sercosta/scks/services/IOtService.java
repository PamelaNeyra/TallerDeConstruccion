package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;


public interface IOtService {
	
	/**
	 * Lógica de negocios para
	 * - O3 - Listar muestra OT
	 * : Lista las muestras con ot con filtro: planta
	 * @return List<Muestra>
	 */
	public abstract List<Muestra> listarMuestraOt(Planta planta);
	
	/**
	 * Lógica de negocios para
	 * - 03 - Obtener Muestra OT
	 * : Obtiene una muestra OT con filtro: muestra
	 * @param Muestra
	 * @return
	 */
	public abstract Muestra obtenerMuestraOt(Muestra muestra);
		
		
	/**
	 * Lógica de negocios para
	 * - O3 - Actualizar Muestra OT
	 * : Actualizar una muestra OT
	 * @param Muestra
	 */
	public abstract void actualizarMuestraOt(Muestra muestra);

}
