package com.pe.sercosta.scks.repositories.implementation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//TODO: Adecuar a las interfaces necesarias
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.exceptions.SercostaException;

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestraProcedimientoAlmacenado", query = "CALL registrarMuestra()", resultClass = Muestra.class) })
// TODO: falta el implements IMuestraRepository
public class MuestraRepository {

	private static final Log LOG = LogFactory.getLog(MuestraRepository.class);
	private static final String CAPA = "[Repository : Muestra] -> ";

	// @Override
	public void registrarMuestra(Session sesion, Muestra muestra) {
		try {
			// TODO: Call PA_RegistrarMuestra()
			sesion.save(muestra);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la muestra", ex.getMessage());
		}
	}

}
