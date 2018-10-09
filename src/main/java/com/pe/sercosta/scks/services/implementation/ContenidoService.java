package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.services.IContenidoService;

@Service("contenidoService")
public class ContenidoService implements IContenidoService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Contenido] -> ";
	
	private IContenidoRepository contenidoRepository;
	
	@PersistenceContext
    private EntityManager sesion;
	
	
	@Override
	public void registrarContenido(Contenido contenido) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			//TODO: Validaciones de O1 - Registrar Contenido
			validarRegistrarContenido(contenido);
			contenidoRepository.registrarContenido(sesion, contenido);
			//tx.commit();
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al registrar el contenido", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	@Override
	public List<Contenido> listarContenidos() {
		EntityTransaction tx = sesion.getTransaction();
		tx.begin();
		try {
			return contenidoRepository.listarContenidos(sesion);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			tx.rollback();
			throw new SercostaException("Hubo un error al listar contenido", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	public void validarRegistrarContenido(Contenido contenido) throws Exception {
		if(contenido.getLote().getIdLote() == null)
			throw new Exception("El idLote es requerido.");
		if(contenido.getPresentacion().getIdPresentacion() == null )
			throw new Exception("El idPresentacion es requerido.");
	}

}
