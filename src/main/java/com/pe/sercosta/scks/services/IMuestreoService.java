package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Muestreo;

public interface IMuestreoService {
	
	/**
	 * Lógica de negocios para
	 * - O2 - Registrar Muestreo
	 * : Registra un Muestreo
	 * @param muestra
	 */
	public abstract void registrarMuestreo(Muestreo muestreo);
	
	/**
	 * Lógica de negocio para
	 * - O8 - Ver Saldos
	 * : Lista todos los Muestreos
	 */
	public abstract List<Muestreo> listarMuestreos();
	
	/**
	 * Lógica de negocio para
	 * -O8 - Ver Saldos
	 * :Consulta un muestreo por la Muestra
	 */
	public abstract Muestreo consultarMuestreo(Muestra muestra);
}
