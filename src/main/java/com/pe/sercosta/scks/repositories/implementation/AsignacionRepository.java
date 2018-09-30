package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.exceptions.SercostaException;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarAsignacionProcedimientoAlmacenado", query = "CALL registrarAsignacion()", resultClass = Asignacion.class) })
// TODO: Falta el implements
public class AsignacionRepository {

	private static final Log LOG = LogFactory.getLog(AsignacionRepository.class);
	private static final String CAPA = "[Repository : Asignacion] -> ";
	
	public void registrarAsignacion(Session sesion, Asignacion asignacion) {
		try {
			// TODO: CALL PA_RegistrarAsignacion()
			sesion.save(asignacion);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la asignación", ex.getMessage());
		}
	}

}
