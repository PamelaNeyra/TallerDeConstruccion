package com.pe.sercosta.scks.services;

import java.util.List;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.models.AsignacionSaldoGrupoModel;
import com.pe.sercosta.scks.models.AsignacionSaldoModel;


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
	
	/**
	 * Lógica de negocio para
	 * -O8- Ver Saldos
	 * :Listar AsignacionSaldoModel
	 */
	public abstract List<AsignacionSaldoModel> listarAsignacionesSaldo(Laboratorio labo, Planta planta, Muestra muestra);
	
	/**
	 * Lógica de negocio para
	 * -O8- Ver Saldos
	 * :Listar AsignacionSaldoGrupoModel
	 */
	public abstract List<AsignacionSaldoGrupoModel> listarAsignacionesSaldoGrupo(Laboratorio labo, Planta planta, Muestra muestra);



}
