package com.pe.sercosta.scks.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	
	/**
	 * Registra un lote
	 * en la base de datos.
	 * @param lote
	 */
	public abstract void registrarLote(Lote lote);
	
}
