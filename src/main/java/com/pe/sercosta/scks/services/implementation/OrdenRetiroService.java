package com.pe.sercosta.scks.services.implementation;


import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.OrdenRetiro;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.IOrdenRetiroRepository;
import com.pe.sercosta.scks.services.IOrdenRetiroService;

@Service("loteService")
public class OrdenRetiroService implements IOrdenRetiroService {

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Lote] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("loteRepository")
	private IOrdenRetiroRepository ordenRetiroRepository;
	
	@Autowired
	@Qualifier("contenidoRepository")
	private IContenidoRepository contenidoRepository;

	@Override
	public void registrarOrdenRetiro(OrdenRetiro ordenRetiro) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			//validarRegistrarLote(lote);
			ordenRetiroRepository.registrarOrdenRetiro(sesion, ordenRetiro);
			
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
}