package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.Asignacion;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarAsignacionProcedimientoAlmacenado", query = "CALL registrarAsignacion()", resultClass = Asignacion.class) })
// TODO: Falta el implements
public class AsignacionRepository {

	public void registrarAsignacion(Session sesion, Asignacion asignacion) {
		try {
			// TODO: CALL PA_RegistrarAsignacion()
			sesion.save(asignacion);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
