package com.pe.sercosta.scks.services.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.services.IContenidoService;

@Service("contenidoService")
public class ContenidoService implements IContenidoService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Contenido] -> ";
	
	@Autowired
	@Qualifier("contenidoRepository")
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
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			return contenidoRepository.listarContenidos(sesion);
			//tx.commit();
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar contenido", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	@Override
	public void actualizarContenido(Contenido contenido) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			contenidoRepository.actualizarContenido(sesion,contenido);
			//tx.commit();
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al actualizar contenido", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	//Falta hacer la capa de datos y el procedure
	@Override
	public List<Lote> listarLotePorPresentacion(Presentacion presentacion) {
		List<Lote> listaLotes = new ArrayList<Lote>();
		List<Contenido> listaContenidos = new ArrayList<Contenido>();
		try {
			//crear un método que haga un listarContenidosPorPresentacion
			listaContenidos = contenidoRepository.listarContenidos(sesion);
			for(Contenido c : listaContenidos){
				Lote aux = new Lote();
				aux = c.getLote();
				listaLotes.add(aux);
			}		
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar Lotes por Presentacion", ex.getMessage());
		} finally {
			sesion.close();
		}
		return listaLotes;
	}
	
	public void validarRegistrarContenido(Contenido contenido) throws Exception {
		if(contenido.getLote().getIdLote() == null)
			throw new Exception("El idLote es requerido.");
		if(contenido.getPresentacion().getIdPresentacion() == null )
			throw new Exception("El idPresentacion es requerido.");
	}

	@Override
	public List<Contenido> listarContenidosPorLote(Lote lote) {
		try {
			 return contenidoRepository.listarContenidosPorLote(sesion, lote);		
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar Contenidos por Lote", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	@Override
	public List<Contenido> listarContenidosPorAsignacion(Asignacion asignacion) {
		try {
			 return contenidoRepository.listarContenidosPorAsignacion(sesion, asignacion);		
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar Lotes por Presentacion", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
