package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import com.pe.sercosta.scks.entities.OrdenVenta;

//TODO: falta revisar que parametros se le manda
@NamedNativeQueries({
		@NamedNativeQuery(name = "registrarOrdenVentaProcedimientoAlmacenado", query = "CALL registrarOrdenVenta()", resultClass = OrdenVenta.class) })
// TODO: Falta el implements
public class OrdenVentaRepository {

	public void registrarOrdenVenta(Session sesion, OrdenVenta ordenVenta) {
		try {
			// TODO: CALL PA_RegistrarOrdenVenta()
			sesion.save(ordenVenta);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
