package com.pe.sercosta.scks.services;

import com.pe.sercosta.scks.entities.Lote;

public interface ILoteService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Registra un lote y su contenido
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);
	
}
