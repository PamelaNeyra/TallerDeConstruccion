package com.pe.sercosta.scks.services;


import java.util.List;

import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Retiro;

public interface IRetiroService {
	
		
	/**
	 * Lógica de negocios para
	 * - O4 - Registrar Retiro
	 * : Registra una retiro
	 * @param retiro
	 */
	public abstract void registrarRetiro(Retiro retiro);
	
	/**
	 * Lógica de negocios para
	 * Ver Orden de Retiro
	 * @param orden
	 * @return
	 */
	public List<Retiro> listarRetiroOrdenRetiro(OrdenRetiro orden);
	
}
