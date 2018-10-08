package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.OrdenVenta;


public interface IAsignacionService {

	/**
	 * Lógica de negocios para
	 * - O6 - Listar Asignacion
	 * :Lstar asignaciones
	 */
	public abstract List<Asignacion> listarAsignacion(OrdenVenta orden);
	
}
