package com.pe.sercosta.scks.services.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.AsignacionModel;
import com.pe.sercosta.scks.repositories.IAsignacionRepository;
import com.pe.sercosta.scks.services.IAsignacionService;

@Service("asignacionService")
public class AsignacionService implements IAsignacionService {
	
	private static final Log LOG = LogFactory.getLog(AsignacionService.class);
	private static final String CAPA = "[Service : Asignacion] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("asignacionRepository")
	private IAsignacionRepository asignacionRepository;
	
	@Override
	public List<AsignacionModel> listarAsignacion(OrdenVenta orden) {
		try {
			return asignacionRepository.listarAsignacion(sesion, orden);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las asignaciones", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
