package com.pe.sercosta.scks.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import com.pe.sercosta.scks.entities.Cliente;


public interface IClienteRepository {

	/*
	public abstract List<Presentacion> listarClientes(EntityManager sesion);
	*/
	
	/**
	 * Lista los clientes 
	 * @param sesion
	 * @return List<Presentacion>
	 */
	public abstract List<Cliente> listarClientes(EntityManager sesion);
	
	/**
	 * Lista los datos de 
	 * un cliente
	 * @param sesion
	 * @param cliente
	 * @return List<Presentacion>
	 */
	public abstract List<Cliente> buscarCliente(EntityManager sesion, Cliente cliente);
	
	/**
	 * Registra los datos de 
	 * un cliente
	 * @param sesion
	 * @param cliente
	 */
	public abstract void registrarCliente(EntityManager sesion, Cliente cliente);
	
	/**
	 * Actualiza un Cliente
	 * de la base de datos
	 * @param sesion
	 * @param cliente
	 */
	public abstract void actualizarCliente(EntityManager sesion, Cliente cliente);
	
	/**
	 * Elimina un Cliente 
	 * de la base de datos
	 * @param sesion
	 * @param cliente
	 */
	public abstract void eliminarCliente(EntityManager sesion, Cliente cliente);
	
}
