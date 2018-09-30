package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.exceptions.SercostaException;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarOrdenVentaProcedimientoAlmacenado", query = "CALL registrarOrdenVenta()", resultClass = OrdenVenta.class) })
// TODO: Falta el implements
public class OrdenVentaRepository {

	private static final Log LOG = LogFactory.getLog(OrdenVentaRepository.class);
	private static final String CAPA = "[Repository : OrdenVenta] -> ";
	
	public void registrarOrdenVenta(Session sesion, OrdenVenta ordenVenta) {
		try {
			// TODO: CALL PA_RegistrarOrdenVenta()
			sesion.save(ordenVenta);
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de venta", ex.getMessage());
		}
	}

}
