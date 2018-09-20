package com.pe.sercosta.scks.services;

import com.pe.sercosta.scks.entities.Lote;

public interface ILoteService {

	/**
	 * Registra un nuevo lote en la base de datos
	 * mediante un procedimiento almacenado
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);
	
}
