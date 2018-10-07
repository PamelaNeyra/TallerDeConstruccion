package com.pe.sercosta.scks.repositories.implementation;

// import java.io.Serializable; /*Descomentar*/
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

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
			//Nombre de PA por verificar
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_orden_venta");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(5, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(6, LocalTime.class, ParameterMode.IN)
					.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
			myquery.setParameter(1, ordenVenta.getIdOrdenVenta())
					.setParameter(2, ordenVenta.getIdPlanta().getIdPlanta())
					.setParameter(3, ordenVenta.getIdCliente().getIdCliente())
					.setParameter(4, ordenVenta.getFechaAsignacion())
					.setParameter(5, ordenVenta.getFechaEmbarque())
					.setParameter(6, ordenVenta.getHoraEmbarque())
					.setParameter(7, ordenVenta.getPaisDestino());
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de venta", ex.getMessage());
		}
	}

}
