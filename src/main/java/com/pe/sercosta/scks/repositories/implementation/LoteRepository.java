package com.pe.sercosta.scks.repositories.implementation;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.ILoteRepository;

@Repository("loteRepository")
public class LoteRepository implements ILoteRepository {

	private static final Log LOG = LogFactory.getLog(LoteRepository.class);
	private static final String CAPA = "[Repository : Lote] -> ";
	
	@Override
	public void registrarLote(EntityManager sesion, Lote lote) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_lote");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
			myquery.setParameter(1, lote.getIdLote())
					.setParameter(2, lote.getIdPlanta().getIdPlanta())
					.setParameter(3, lote.getFechaProduccion())
					.setParameter(4, lote.getCantidadRecepcion());
			myquery.execute();			
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		}
	}

}
