package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.models.InfoLoteMuestreoModel;
import com.pe.sercosta.scks.models.InfoMuestraModel;

public interface IMuestraRepository {

	/**
	 * Registra una muestra
	 * en la base de datos.
	 * @param sesion
	 * @param muestra
	 */
	public abstract void registrarMuestra(EntityManager sesion, Muestra muestra);
	
	/**
	 * Lista todas las Muestras
	 * de la base de datos segun planta.
	 * @param sesion
	 * @param planta
	 * @return List<Muestra>
	 */
	public abstract List<Muestra> listarMuestraOt(EntityManager sesion,Planta planta);
	
	/**
	 * Obtiene la información de una
	 * muestra.
	 * @param sesion
	 * @param muestra
	 * @return
	 */
	public abstract Muestra obtenerMuestraOt(EntityManager sesion, Muestra muestra);
	
	/**
	 * Actualiza una muestra
	 * de la base de datos segun planta e id de la muestra.
	 * @param sesion
	 * @param muestra
	 */
	public abstract void actualizarMuestraOt(EntityManager sesion, Muestra muestra);
	
	/**
	 * Lista las InfoMuestraModel
	 * 
	 */
	public abstract InfoMuestraModel obtenerInfoMuestra(EntityManager sesion, Planta planta, Muestra muestra);
	
	/**
	 * Lista los InfoLoteMuestreoModel
	 */
	public abstract List<InfoLoteMuestreoModel> listarInfoLoteMuestreo(EntityManager sesion, Planta planta, Muestra muestra);
}
