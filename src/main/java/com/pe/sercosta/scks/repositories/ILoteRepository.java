package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Lote;

public interface ILoteRepository {
	
	/**
	 * Registra un lote
	 * en la base de datos.
	 * @param sesion
	 * @param lote
	 */
	public abstract void registrarLote(EntityManager sesion, Lote lote);

	/**
	 * Actualiza un lote
	 * en la base de datos.
	 * @param sesion
	 * @param lote
	 */
	public abstract void actualizarLote(EntityManager sesion, Lote lote);

	
	/**
	 * Lista los lotes
	 * en la base de datos.
	 * @param sesion
	 * @param lote
	 */
	public abstract List<Lote> listarLotes(EntityManager sesion);
	
}
