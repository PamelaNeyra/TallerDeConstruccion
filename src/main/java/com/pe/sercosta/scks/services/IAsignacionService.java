package com.pe.sercosta.scks.services;

import java.util.List;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.models.AsignacionModel;


public interface IAsignacionService {

	/**
	 * Lógica de negocios para
	 * - O6 - Listar Asignacion
	 * :Lstar asignaciones
	 */
	public abstract List<AsignacionModel> listarAsignacion(OrdenVenta orden);
	
}
