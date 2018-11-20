package com.pe.sercosta.scks.services;

import java.util.List;


import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.models.LoteOtModel;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;

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
	 * Lista los lotes
	 * en la base de datos.
	 * @param sesion
	 * @param planta
	 * @return List<Lote>
	 */
	public abstract List<Lote> listarLotesPlanta(Planta planta);
	
	/**
	 * Lógica de negocios para
	 * - O3 - Listar Lote
	 * : Listar los lote
	 * @param muestra
	 */
	public abstract List<LoteOtModel> listarLotesOt(Muestra muestra);
	
	/**
	 * Lógica de negocios para
	 * -O8 - Listar lote por presentacion
	 * @param presentacion
	 */
	public abstract List<Lote> listarLotePorPresentacion(Presentacion presentacion);
}
