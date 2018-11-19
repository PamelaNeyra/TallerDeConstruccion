package com.pe.sercosta.scks.repositories.implementation;


import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IOrdenRetiroRepository;

@Repository("ordenVentaRepository")
public class OrdenRetiroRepository implements IOrdenRetiroRepository{

	private static final Log LOG = LogFactory.getLog(OrdenVentaRepository.class);
	private static final String CAPA = "[Repository : OrdenVenta] -> ";
	
	
	

	@Override
	public void registrarOrdenRetiro(EntityManager sesion, OrdenRetiro ordenRetiro) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_orden_retiro");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, LocalDate.class, ParameterMode.IN)
					.registerStoredProcedureParameter(5, Double.class, ParameterMode.IN);
				//.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);
			myquery.setParameter(1, ordenRetiro.getIdOrdenRetiro())
					.setParameter(2, ordenRetiro.getIdPlanta().getIdPlanta())
					.setParameter(3, ordenRetiro.getIdMotivo())
					.setParameter(4, ordenRetiro.getFechaRetiro())
					.setParameter(5, ordenRetiro.getCantidadTotal());
	
			//	.setParameter(7, ordenVenta.getPaisDestino()); 
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de retiro", ex.getMessage());
		}
		
	}
}