package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.ProductoTerminado;

public interface IProductoTerminadoRepository {

	/**
	 * Lista los Productos Terminados
	 * de la base de datos.
	 * @param sesion
	 * @param ProductoTerminado
	 */
	public abstract List<ProductoTerminado> listarProductoTerminado(EntityManager sesion);
}
