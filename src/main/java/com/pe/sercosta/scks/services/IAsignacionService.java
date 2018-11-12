package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.AsignacionModel;


public interface IAsignacionService {

	/**
	 * Lógica de negocios para
	 * - O6 - Listar Asignacion
	 * :Lstar asignaciones
	 */
	public abstract List<AsignacionModel> listarAsignacion(OrdenVenta orden);
	
	/**
	 * Lógica de negocio para
	 * -08 - Listar Asignacion por Presentación
	 * :Listar asignaciones
	 */
	public abstract List<Asignacion> listarAsignacionPorPresentacion(Presentacion presentacion);
	
	/**
	 * Lógica de negocio para
	 * -O9 Ver packings
	 * :Listar asignaciones
	 */
	public abstract List<Asignacion> listarAsignacion();
}
