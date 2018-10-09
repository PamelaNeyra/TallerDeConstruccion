package com.pe.sercosta.scks.repositories.implementation;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IMuestreoRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestreoProcedimientoAlmacenado", query = "CALL registrarMuestreo()", resultClass = Muestreo.class) })
@Repository("muestreoRepository")
public class MuestreoRepository implements IMuestreoRepository {

	private static final Log LOG = LogFactory.getLog(MuestreoRepository.class);
	private static final String CAPA = "[Repository : Muestreo] -> ";	
	
	// @Override
	public void registrarMuestreo(EntityManager sesion, Muestreo muestreo) {
		try {
			// TODO: Call PA_RegistrarMuestreo()
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_muestreo");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
			myquery.setParameter(1, muestreo.getMuestreoPK().getIdLote())
					.setParameter(2, muestreo.getMuestreoPK().getIdPresentacion())
					.setParameter(3, muestreo.getMuestreoPK().getIdMuestra())
					.setParameter(4, muestreo.getCantidad());
			myquery.execute();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el muestreo", ex.getMessage());
		}
	}

}