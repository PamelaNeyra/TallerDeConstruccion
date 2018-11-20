package com.pe.sercosta.scks.repositories.implementation;


import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pe.sercosta.scks.entities.Retiro;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IRetiroRepository;

@Repository("retiroRepository")
public class RetiroRepository implements IRetiroRepository{

	private static final Log LOG = LogFactory.getLog(RetiroRepository.class);
	private static final String CAPA = "[Repository : Retiro] -> ";
		
	@Override
	public void registrarRetiro(EntityManager sesion, Retiro retiro) {
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_registrar_retiro");
			myquery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Double.class, ParameterMode.IN);
			myquery.setParameter(1, retiro.getRetiroPK().getIdLote())
					.setParameter(2, retiro.getRetiroPK().getIdPresentacion())
					.setParameter(4, retiro.getCantidad());
			myquery.execute();	
		} catch (Exception ex) {
			LOG.error(CAPA + ex);
			throw new SercostaException("Hubo un error al registrar el retiro", ex.toString());
		}
		
	}
}