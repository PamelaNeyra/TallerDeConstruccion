package com.pe.sercosta.scks.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Contenido;

@Repository("contenidoRepository")
public interface IContenidoRepository extends JpaRepository<Contenido, Serializable> {
	
	/**
	 * Registra un contenido
	 * en la base de datos.
	 * @param contenido
	 */
	public abstract void registrarContenido(Contenido contenido);
	
}
