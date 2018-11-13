package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.models.SaldoOtModel;

public interface IMuestreoRepository {

	/**
	 * Registra un muestreo
	 * en la base de datos.
	 * @param sesion
	 * @param muestreo
	 */
	public abstract void registrarMuestreo(EntityManager sesion , Muestreo muestreo);
	
	/**
	 * Lista los Muestreo
	 * de la base de datos
	 */
	public abstract List<Muestreo> listarMuestreos(EntityManager sesion);
	
	/**
	 * Lista los Saldos 
	 * por OT
	 */
	public abstract List<SaldoOtModel> listarSaldoOt(EntityManager sesion, Planta planta);
	
}
