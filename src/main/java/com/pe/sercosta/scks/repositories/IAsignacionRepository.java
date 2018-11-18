package com.pe.sercosta.scks.repositories;


import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Laboratorio;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.models.AsignacionSaldoGrupoModel;
import com.pe.sercosta.scks.models.AsignacionSaldoModel;
import com.pe.sercosta.scks.models.InfoAsignacionModel;
import com.pe.sercosta.scks.models.OrdenVentaClienteModel;


public interface IAsignacionRepository {
	

	/**
	 * Lista la asignacion que
	 * cumplan con el filtro de la
	 * planta e id de orden de venta.
	 * @param sesion
	 * @param asignacion
	 * @param planta
	 * @return List<Asignacion>
	 */
	public abstract List<AsignacionModel> listarAsignacion(EntityManager sesion, OrdenVenta orden);
	
	/**
	 * Registra una asignacion
	 * en la base de datos.
	 * @param sesion
	 * @param asignacion
	 */
	public abstract void registrarAsignacion(EntityManager sesion, Asignacion asignacion);
	
	/**
	 * Embarca una asignacion
	 * en la base de datos.
	 * @param sesion
	 * @param asignacion
	 */
	public abstract void embarcarAsignacion(EntityManager sesion, Asignacion asignacion);
	
	/**
	 * Lista todas las asignaciones
	 * de la base de datos
	 * y lo guarda en un model
	 * @param sesion
	 */
	public abstract List<Asignacion> listarAsignaciones(EntityManager sesion, Planta planta);
	
	/**
	 * Lista las asignaciones 
	 * de la base de datos
	 * y guarda en un model
	 */
	public abstract List<AsignacionSaldoModel> listarAsignacionesSaldo(EntityManager sesion, Laboratorio laboratorio, Planta planta,
															Muestra muestra);
	
	/**
	 * Lista las asignaciones
	 * de la base de datos
	 * y agrupa
	 */
	public abstract List<AsignacionSaldoGrupoModel> listarAsignacionesSaldoGrupo(EntityManager sesion, Laboratorio laboratorio, Planta planta,
															Muestra muestra );
	
	/**
	 * Lista las ordenes de venta y clientes
	 * de la base de datos. Luego los añade a 
	 * OrdenVentaClienteModel
	 */
	public abstract List<OrdenVentaClienteModel> listarOrdenVentaCliente(EntityManager sesion, Planta planta);
	
	/**
	 * Lista las info Asignacion Model
	 */
	public abstract List<InfoAsignacionModel> listarInfoAsignacion(EntityManager sesion, Planta planta, OrdenVenta orden);
}
