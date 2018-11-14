package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.LoteOtModel;

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
	 * @return List<Lote>
	 */
	public abstract List<Lote> listarLotes(EntityManager sesion);
	
	/**
	 * Elimina un Lote
	 * de la base de datos
	 * @param sesion
	 * @param lote
	 */
	public abstract void eliminarLote(EntityManager sesion, Lote lote);
	
	/**
	 * Lista lotes 
	 * @param sesion
	 * @param muestra
	 * @return List<Asignacion>
	 */
	public abstract List<LoteOtModel> listarLoteOt(EntityManager sesion, Muestra muestra);
	
	/**
	 * Lista lotes por Presentacion
	 * @param presentacion
	 * @return List<Lote>
	 */
	public abstract List<Lote> listarLotePorPresentacion(EntityManager sesion, Presentacion presentacion);
	
}
