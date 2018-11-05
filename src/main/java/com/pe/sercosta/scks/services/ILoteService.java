package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Muestra;

public interface ILoteService {

	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Lote
	 * : Registra un lote y su contenido
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);

	/**
	 * Lógica de negocios para
	 * - O2 - Actualizar Lote
	 * : Actualiza un lote
	 * @param lote
	 */
	public abstract void actualizarLote(Lote lote);

	/**
	 * Lógica de negocios para
	 * - O2 - Listar Lote
	 * : Lista los lotes
	 * @param lote
	 */
	public abstract List<Lote> listarLotes();
	
	/**
	 * Lógica de negocios para
	 * - O3 - Listar Lote
	 * : Listar los lote
	 * @param muestra
	 */
	public abstract List<Lote> listarLotesOT(Muestra muestra);
	
	
}
