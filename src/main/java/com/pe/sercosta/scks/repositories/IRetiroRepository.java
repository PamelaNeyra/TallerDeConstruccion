package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Retiro;

public interface IRetiroRepository {
	
	
	/**
	 * Registra un retiro
	 * en la base de datos.
	 * @param sesion
	 * @param retiro
	 */
	public abstract void registrarRetiro(EntityManager sesion, Retiro retiro);
	
	/**
	 * Lista los retiros
	 * de una orden de retiro.
	 * @param sesion
	 * @param orden
	 * @return
	 */
	public abstract List<Retiro> listarRetiroOrdenRetiro(EntityManager sesion, OrdenRetiro orden);
	
}
