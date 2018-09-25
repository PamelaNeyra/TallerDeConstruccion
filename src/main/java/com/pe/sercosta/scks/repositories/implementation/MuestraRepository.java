package com.pe.sercosta.scks.repositories.implementation;

//TODO: Adecuar a las interfaces necesarias
import org.hibernate.Session;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import com.pe.sercosta.scks.entities.Muestra;
//import com.pe.sercosta.scks.repositories.IMuestraRepository; 

@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarMuestraProcedimientoAlmacenado", query = "CALL registrarMuestra()", resultClass = Muestra.class) })
// TODO: falta el implements IMuestraRepository
public class MuestraRepository {

	// @Override
	public void registrarMuestra(Session sesion, Muestra muestra) {
		try {
			// TODO: Call PA_RegistrarMuestra()
			sesion.save(muestra);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
