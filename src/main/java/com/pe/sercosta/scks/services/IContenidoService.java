package com.pe.sercosta.scks.services;

import com.pe.sercosta.scks.entities.Contenido;

public interface IContenidoService {

	/**
	 * Registra un nuevo contenido en la base de datos
	 * mediante un procedimiento almacenado
	 * @param contenido
	 */
	public abstract void registrarContenido(Contenido contenido);
}
