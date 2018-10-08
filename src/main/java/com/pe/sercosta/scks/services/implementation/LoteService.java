package com.pe.sercosta.scks.services.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Lote] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("loteRepository")
	private ILoteRepository loteRepository;
	
	@Autowired
	@Qualifier("contenidoRepository")
	private IContenidoRepository contenidoRepository;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void registrarLote(Lote lote) {
		EntityTransaction tx = sesion.getTransaction();
		boolean errorValidacion = false;
		tx.begin();
		try {
			//TODO: Validaciones de O1 - Registrar Lote
			if(lote.getIdPlanta() != null || lote.getIdPlanta().equals("")) {
				if(lote.getIdLote() != null  || lote.getIdLote().equals("")) {
					if(lote.getFechaProduccion() != null) {
						if(lote.getFechaCaptura() != null) {
							if(lote.getFechaVencimiento() != null) {
								loteRepository.registrarLote(sesion, lote);
								lote.getContenidoList().forEach(c -> contenidoRepository.registrarContenido(sesion, c));
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
				}else {
					errorValidacion = true;					
				}
			}else {
				errorValidacion = true;
			}
			if(errorValidacion == true) {
				LOG.error("Error al validar los campos de Lote");
				throw new Exception();
			}
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			tx.rollback();
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
