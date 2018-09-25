package com.pe.sercosta.scks.services;

import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Lote;

@Service("loteService")
public interface ILoteService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Registra un lote y su contenido
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);
	
}
