package com.pe.sercosta.scks.services;

import com.pe.sercosta.scks.entities.Muestreo;

public interface IMuestreoService {
	
	/**
	 * Lógica de negocios para
	 * - O2 - Registrar Muestreo
	 * : Registra un Muestreo
	 * @param muestra
	 */
	public abstract void registrarMuestreo(Muestreo muestreo);
}
