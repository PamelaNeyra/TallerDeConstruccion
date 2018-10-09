package com.pe.sercosta.scks.repositories.implementation;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.stereotype.Repository;

import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IMuestraRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestraProcedimientoAlmacenado", query = "CALL registrarMuestra()", resultClass = Muestra.class) })
@Repository("muestraRepository")
public class MuestraRepository implements IMuestraRepository {

	private static final Log LOG = LogFactory.getLog(MuestraRepository.class);
	private static final String CAPA = "[Repository : Muestra] -> ";

	// @Override
	public void registrarMuestra(EntityManager sesion, Muestra muestra) {
		try {
			// TODO: Call PA_RegistrarMuestra()
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_muestra");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(5, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(7, Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(9, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, muestra.getIdMuestra())
					.setParameter(2, muestra.getFechaCreacion())
					.setParameter(3, muestra.getIdLaboratorio())
					.setParameter(4, 2)
					.setParameter(5, null)
					.setParameter(6, null)
					.setParameter(7, muestra.getCantidadTotal())
					.setParameter(8, 1)
					.setParameter(9, 0);
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la muestra", ex.getMessage());
		}
	}

}
