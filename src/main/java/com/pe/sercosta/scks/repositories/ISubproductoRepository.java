package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Subproducto;

public interface ISubproductoRepository {
	/**
	 * Lista los Subproductos
	 * de la base de datos
	 * @param sesion
	 * @param subproducto
	 * @return List<Subproducto>
	 */
	public abstract List<Subproducto> listarSubproducto(EntityManager sesion, Subproducto subproducto);
	
	/**
	 * Registra un Subproducto
	 * en la base de datos
	 * @param sesion
	 * @param subproducto
	 */
	public abstract void registrarSubproducto(EntityManager sesion, Subproducto subproducto);
	
	/**
	 * Actualiza un Subproducto
	 * en la base de datos
	 * @param sesion
	 * @param subproducto
	 */
	public abstract void actualizarSubproducto(EntityManager sesion, Subproducto subproducto);
	
	/**
	 * Elimina un Subproducto
	 * en la base de datos
	 * @param sesion
	 * @param subproducto
	 */
	public abstract void eliminarSubproducto(EntityManager sesion, Subproducto subproducto);

}
