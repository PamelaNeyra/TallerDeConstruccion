package com.pe.sercosta.scks.repositories.implementation;


import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pe.sercosta.scks.entities.Motivo;
import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.entities.Retiro;
import com.pe.sercosta.scks.entities.RetiroPK;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Retiro> listarRetiroOrdenRetiro(EntityManager sesion, OrdenRetiro orden) {
		List<Retiro> listaRetiro = new ArrayList<Retiro>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_retiro_orden_retiro");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, orden.getIdOrdenRetiro());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Retiro aux = new Retiro();
				RetiroPK pk = new RetiroPK();
				pk.setIdPresentacion((String) o[0]);
				pk.setIdLote((String) o[1]);
				aux.setCantidad((Double) o[2]);
				aux.setRetiroPK(pk);
				listaRetiro.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los retiros", ex.getMessage());
		}
		return listaRetiro;
	}
}