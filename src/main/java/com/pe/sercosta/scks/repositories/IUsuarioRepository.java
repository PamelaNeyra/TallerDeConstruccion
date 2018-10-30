package com.pe.sercosta.scks.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import com.pe.sercosta.scks.entities.Usuario;

public interface IUsuarioRepository {
	/**
	 * Lista todos los usuarios
	 * de la base de datos
	 * @param sesion
	 * @return List<Usuario>
	 */
	public abstract List<Usuario> listarUsuario(EntityManager sesion);
	
	/**
	 * Registra un Usuario
	 * en la base de datos
	 * @param sesion
	 * @param usuario
	 */
	public abstract void registrarUsuario(EntityManager sesion, Usuario usuario);
	
	/**
	 * Actualiza un Usuario
	 * de la base de datos
	 * @param sesion
	 * @param usuario
	 */
	public abstract void actualizarUsuario(EntityManager sesion, Usuario usuario);
	
	/**
	 * Elimina un Usuario
	 * de la base de datos
	 * @param sesion
	 * @param usuario
	 */
	public abstract void eliminarUsuario(EntityManager sesion, Usuario usuario);
}
