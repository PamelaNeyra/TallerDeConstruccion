package com.pe.sercosta.scks.repositories;

import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository{
	
	/**
	 * Registra un lote
	 * en la base de datos.
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);
	
}
