package com.pe.sercosta.scks.repositories;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository {
	
	/**
	 * Registra un lote
	 * en la base de datos.
	 * @param sesion
	 * @param lote
	 */
	public abstract void registrarLote(Session sesion, Lote lote);
	
}
