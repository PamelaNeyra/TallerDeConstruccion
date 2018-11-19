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
import com.pe.sercosta.scks.repositories.IOrdenRetiroRepository;
import com.pe.sercosta.scks.repositories.IRetiroRepository;
import com.pe.sercosta.scks.services.IOrdenRetiroService;

@Service("ordenRetiroService")
public class OrdenRetiroService implements IOrdenRetiroService {

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : OrdenRetiro] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("ordenRetiroRepository")
	private IOrdenRetiroRepository ordenRetiroRepository;
	
	@Autowired
	@Qualifier("retiroRepository")
	private IRetiroRepository retiroRepository;

	@Override
	public void registrarOrdenRetiro(OrdenRetiro ordenRetiro) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			validaregistrarOrdenRetiro(ordenRetiro);
			ordenRetiroRepository.registrarOrdenRetiro(sesion, ordenRetiro);	
			ordenRetiro.getRetiroList().forEach(retiro -> {
					retiroRepository.registrarRetiro(sesion, retiro);
				}
			);
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
	
	private void validaregistrarOrdenRetiro(OrdenRetiro ordenRetiro) throws Exception {
		if(ordenRetiro.getIdPlanta().getIdPlanta() == null)
			throw new Exception("El id de Planta es requerido.");
		if(ordenRetiro.getIdMotivo().getIdMotivo() == null)
			throw new Exception("El id de Motivo es requerido.");
		if(ordenRetiro.getFechaRetiro() == null)
			throw new Exception("La fecha de retiro es requerida");
		if(ordenRetiro.getRetiroList() == null || ordenRetiro.getRetiroList().isEmpty())
			throw new Exception("La lista de retiros es requerida.");
	}
	
}