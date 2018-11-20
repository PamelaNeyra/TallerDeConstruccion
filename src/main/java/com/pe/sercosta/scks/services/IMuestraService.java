package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.models.InfoMuestraModel;

public interface IMuestraService {

	/**
	 * Lógica de negocios para
	 * - O2 - Registrar Muestra
	 * : Registra una muestra y su cantidad a muestrear
	 * @param muestra
	 */
	public abstract void registrarMuestra(Muestra muestra, List<ProductoTerminado> listaPresentacion);
	
	/**
	 * Lógica de negocios para
	 * - O8 - Ver Saldos
	 * :lista todas las muestras
	 */
	public abstract List<Muestra> listarMuestras();
	
	/**
	 * Lógica de negocios para
	 * -O9 - Ver Packings
	 * :lista todas las InfoMuestra
	 */
	public abstract List<InfoMuestraModel> listarInfoMuestra(Planta planta, Muestra muestra);
}