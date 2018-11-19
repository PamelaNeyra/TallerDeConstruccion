package com.pe.sercosta.scks.services.implementation;


import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Retiro;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.IRetiroRepository;
import com.pe.sercosta.scks.services.IRetiroService;

@Service("retiroService")
public class RetiroService implements IRetiroService {

	private static final Log LOG = LogFactory.getLog(RetiroService.class);
	private static final String CAPA = "[Service : Retiro] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("retiroRepository")
	private IRetiroRepository retiroRepository;
	
	@Autowired
	@Qualifier("contenidoRepository")
	private IContenidoRepository contenidoRepository;

	@Override
	public void registrarRetiro(Retiro retiro) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			//validarRegistrarLote(lote);
			retiroRepository.registrarRetiro(sesion, retiro);
			
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