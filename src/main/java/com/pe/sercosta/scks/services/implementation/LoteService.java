package com.pe.sercosta.scks.services.implementation;

import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
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

	@Override
	public void registrarLote(Lote lote) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			validarRegistrarLote(lote);
			loteRepository.registrarLote(sesion, lote);
			lote.getContenidoList().forEach(c -> contenidoRepository.registrarContenido(sesion, c));			
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
	
	private void validarRegistrarLote(Lote lote) throws Exception {
		if(lote.getIdLote().isEmpty() || lote.getIdLote() == null)
			throw new Exception("El idLote es requerido.");
		if(lote.getIdPlanta().getIdPlanta() == null)
			throw new Exception("El idPlanta es requerido.");
		if(lote.getCantidadRecepcion() <= 0.0)
			throw new Exception("La cantidadRecepcion debe ser mayor a 0");
		if(lote.getFechaProduccion() == null)
			throw new Exception("La fechaProduccion es requerida");
		if(lote.getContenidoList() == null)
			throw new Exception("La lista de contenidos es requerida.");
	}

}
