package com.pe.sercosta.scks.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Laboratorio;

public interface ILaboratorioRepository {

	/**
	 * Lista todos los laboratorios
	 * de la base de datos.
	 * @param sesion
	 * @return List<Laboratorio>
	 */
	public abstract List<Laboratorio> listarLaboratorios(EntityManager sesion);
	
	/**
	 * Registra un laboratorio
	 * en la base de datos.
	 * @param sesion
	 * @param labo
	 */
	public abstract void registrarLaboratorio(EntityManager sesion , Laboratorio laboratorio);
	
	/**
	 * Actualiza un laboratorio
	 * de la base de datos.
	 * @param sesion
	 * @param labo
	 */
	public abstract void actualizarLaboratorio(EntityManager sesion ,Laboratorio laboratorio);
	
	/**
	 * Elimina un laboratorio
	 * de la base de datos.
	 * @param sesion
	 * @param labo
	 */
	public abstract void eliminarLaboratorio(EntityManager sesion , Laboratorio laboratorio);
}
