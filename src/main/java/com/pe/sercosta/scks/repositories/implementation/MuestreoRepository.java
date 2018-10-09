package com.pe.sercosta.scks.repositories.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IMuestraRepository;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestreoProcedimientoAlmacenado", query = "CALL registrarMuestreo()", resultClass = Muestreo.class) })
@Repository("muestreoRepository")
public class MuestreoRepository implements IMuestraRepository {

	private static final Log LOG = LogFactory.getLog(MuestreoRepository.class);
	private static final String CAPA = "[Repository : Muestreo] -> ";	
	
	// @Override
	public void registrarMuestreo(Session sesion, Muestreo muestreo) {
		try {
			// TODO: Call PA_RegistrarMuestreo()
			sesion.save(muestreo);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el muestreo", ex.getMessage());
		}
	}

}