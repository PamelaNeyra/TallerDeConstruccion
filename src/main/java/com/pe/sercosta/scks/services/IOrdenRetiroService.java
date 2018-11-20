package com.pe.sercosta.scks.services;


import java.util.List;

import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Planta;

public interface IOrdenRetiroService {
	
		
	/**
	 * Lógica de negocios para
	 * - O4 - Registrar OrdenRetiro
	 * : Registra una orden de retiro
	 * @param ordenRetiro
	 */
	public abstract void registrarOrdenRetiro(OrdenRetiro ordenRetiro);
	
	/**
	 * Lógica de negocios para
	 * Ver Orden Retiro
	 * @param planta
	 */
	public abstract List<OrdenRetiro> listarOrdenRetiro(Planta planta);
	
	
}
