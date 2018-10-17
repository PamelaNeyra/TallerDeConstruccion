package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.ProductoTerminado;

public interface IMuestraService {

	/**
	 * Lógica de negocios para
	 * - O2 - Registrar Muestra
	 * : Registra una muestra y su cantidad a muestrear
	 * @param muestra
	 */
	public abstract void registrarMuestra(Muestra muestra, List<ProductoTerminado> listaPresentacion);
	
}