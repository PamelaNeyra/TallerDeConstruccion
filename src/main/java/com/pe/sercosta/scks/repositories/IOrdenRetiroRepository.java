package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;


import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Planta;

public interface IOrdenRetiroRepository {
	
	
	/**
	 * Registra una orden de retiro
	 * en la base de datos.
	 * @param sesion
	 * @param ordenRetiro
	 */
	public abstract void registrarOrdenRetiro(EntityManager sesion, OrdenRetiro ordenRetiro);
	
	/**
	 * Lista las ordenes de retiro
	 * por planta en la base de datos.
	 * @param sesion
	 * @param planta
	 * @return
	 */
	public abstract List<OrdenRetiro> listarOrdenRetiro(EntityManager sesion, Planta planta);
	
}
