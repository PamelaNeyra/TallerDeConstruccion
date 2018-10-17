package com.pe.sercosta.scks.repositories.implementation;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IMuestraRepository;

@Repository("muestraRepository")
public class MuestraRepository implements IMuestraRepository {

	private static final Log LOG = LogFactory.getLog(MuestraRepository.class);
	private static final String CAPA = "[Repository : Muestra] -> ";

	@Override
	public void registrarMuestra(EntityManager sesion, Muestra muestra) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_muestra");
			myquery.registerStoredProcedureParameter(1, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getFechaCreacion())
					.setParameter(2, muestra.getIdLaboratorio().getIdLaboratorio())
					.setParameter(3, muestra.getIdPlanta().getIdPlanta());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la muestra", ex.getMessage());
		}
	}

}
