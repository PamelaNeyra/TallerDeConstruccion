package com.pe.sercosta.scks.services.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.services.IMuestreoService;

@Service("muestreoService")
public class MuestreoService implements IMuestreoService{
	
	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Contenido] -> ";

	private IMuestreoRepository muestreoRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Override
	public void registrarMuestreo(Muestreo muestreo) {
		EntityTransaction tx = sesion.getTransaction();
		boolean errorValidacion = false;
		tx.begin();
		try {
			//TODO: Validaciones de O2 - Muestrear Contenido
			if(muestreo.getMuestreoPK().getIdLote() != null || muestreo.getMuestreoPK().getIdLote().equals("") ) {
				if(muestreo.getMuestreoPK().getIdPresentacion() != null || 
						muestreo.getMuestreoPK().getIdPresentacion().equals("")) {
					if(muestreo.getMuestreoPK().getIdMuestra() != 0) {
						//Falta implementar el IMuestreoRepository
						muestreoRepository.registrarMuestreo(sesion, muestreo);
						tx.commit();
					}else {
						errorValidacion = true;
					}			
				}else {
					errorValidacion = true;
				}
			}else {
				errorValidacion = true;				
			}
			if(errorValidacion == true) {
				LOG.error("Error al validar los campos del Muestreo");
				throw new Exception();
			}
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			tx.rollback();
			throw new SercostaException("Hubo un error al registrar el Muestreo", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
