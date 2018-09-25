package com.pe.sercosta.scks.repositories.implementation;

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

	// @Override
	public void registrarMuestreo(Session sesion, Muestreo muestreo) {
		try {
			// TODO: Call PA_RegistrarMuestreo()
			sesion.save(muestreo);
		} catch (Exception ex) {
			throw ex;
		}
	}

}