package com.pe.sercosta.scks.repositories.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//TODO: Adecuar a las interfaces necesarias
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import com.pe.sercosta.scks.entities.Muestreo;
//import com.pe.sercosta.scks.repositories.IMuestraRepository; 

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestreoProcedimientoAlmacenado", query = "CALL registrarMuestreo()", resultClass = Muestreo.class) })
// TODO: falta el implements IMuestreoRepository
public class MuestreoRepository {

	private static final Log LOG = LogFactory.getLog(MuestreoRepository.class);
	private static final String CAPA = "[Repository : Muestreo] -> ";	
	
	// @Override
	public void registrarMuestreo(Session sesion, Muestreo muestreo) {
		try {
			// TODO: Call PA_RegistrarMuestreo()
			sesion.save(muestreo);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw ex;
		}
	}

}