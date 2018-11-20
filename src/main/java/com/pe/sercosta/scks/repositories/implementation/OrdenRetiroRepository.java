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

@Repository("ordenRetiroRepository")
public class OrdenRetiroRepository implements IOrdenRetiroRepository{

	private static final Log LOG = LogFactory.getLog(OrdenRetiroRepository.class);
	private static final String CAPA = "[Repository : OrdenRetiro] -> ";
	
	
	

	@Override
	public void registrarOrdenRetiro(EntityManager sesion, OrdenRetiro ordenRetiro) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_orden_retiro");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, LocalDate.class, ParameterMode.IN);
			myquery.setParameter(1, ordenRetiro.getIdPlanta().getIdPlanta())
					.setParameter(2, ordenRetiro.getIdMotivo().getIdMotivo())
					.setParameter(3, ordenRetiro.getFechaRetiro());
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar la orden de retiro", ex.getMessage());
		}
		
	}
}