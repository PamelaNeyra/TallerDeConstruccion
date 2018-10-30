package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;

public interface IProductoTerminadoRepository {

	/**
	 * Lista los Productos Terminados
	 * de la base de datos.
	 * @param sesion
	 * @param ProductoTerminado
	 * @return List<ProductoTerminado>
	 */
	public abstract List<ProductoTerminado> listarProductoTerminado(EntityManager sesion,Planta planta);
	
	/**
	 * Registra un Producto Terminado
	 * en la base de datos
	 * @param sesion
	 * @param productoTerminado
	 */
	public abstract void registrarProductoTerminado(EntityManager sesion,ProductoTerminado productoTerminado);
	
	/**
	 * Actualiza un Producto Terminado
	 * en la base de datos
	 * @param sesion
	 * @param productoTerminado
	 */
	public abstract void actualizarProductoTerminado(EntityManager sesion,ProductoTerminado productoTerminado);
	
	/**
	 * Elimina un Producto Terminado
	 * en la base de datos
	 * @param sesion
	 * @param productoTerminado
	 */
	public abstract void eliminarProductoTerminado(EntityManager sesion,ProductoTerminado productoTerminado);
}
