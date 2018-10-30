package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Producto;

public interface IProductoRepository {

	/**
	 * Lista todos los productos 
	 * de la base de da
	 * @param sesion
	 * @return List<Producto>
	 */
	public abstract List<Producto> listarProducto(EntityManager sesion);
	
	/**
	 * Registra un Producto
	 * en la base de datos
	 * @param sesion
	 * @param producto
	 */
	public abstract void registrarProducto(EntityManager sesion, Producto producto);
	
	/**
	 * Actualiza un Producto
	 * en la base de datos
	 * @param sesion
	 * @param producto
	 */
	public abstract void actualizarProducto(EntityManager sesion, Producto producto);
	
	/**
	 * Elimina un Producto
	 * en la base de datos
	 * @param sesion
	 * @param producto
	 */
	public abstract void eliminarProducto(EntityManager sesion, Producto producto);
	
	
}
