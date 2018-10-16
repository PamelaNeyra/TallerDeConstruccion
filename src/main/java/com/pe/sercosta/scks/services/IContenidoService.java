package com.pe.sercosta.scks.services;

import java.util.List;



import com.pe.sercosta.scks.entities.Contenido;

public interface IContenidoService {
	
	/**
	 * Lógica de negocios para
	 * - O1 - Registrar Contenido
	 * : Registra un contenido
	 * @param contenido
	 */
	public abstract void registrarContenido(Contenido contenido);
	
	/**
	 * Lógica de negocios para
	 * - O1 - Listar Contenido
	 * : Lista un contenido
	 * @param contenido
	 */
	public abstract List<Contenido> listarContenidos();

	/**
	 * Lógica de negocios para
	 * - O2 - Actualizar Contenido
	 * : Actualiza un contenido
	 * @param contenido
	 */
	public abstract void actualizarContenido(Contenido contenido);
}
