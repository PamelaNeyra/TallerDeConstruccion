package com.pe.sercosta.scks.services.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.ProductoAsignacionModel;
import com.pe.sercosta.scks.repositories.IPresentacionRepository;
import com.pe.sercosta.scks.services.IPresentacionService;

@Service("presentacionService")
public class PresentacionService implements IPresentacionService {

	private static final Log LOG = LogFactory.getLog(PresentacionService.class);
	private static final String CAPA = "[Service : Presentacion] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("presentacionRepository")
	private IPresentacionRepository presentacionRepository;
	
	@Override
	public List<Presentacion> listarPresentacion() {
		try {
			return presentacionRepository.listarPresentacion(sesion);									
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	

	
	
	@Override
	public List<Presentacion> buscarPresentacion(Presentacion presentacion) {
		// TODO: Pendiente de retirar
		return null;
	}

	@Override
	public List<ProductoAsignacionModel> listarDetalleProductoAsignacion(Planta planta) {
		try {
			return presentacionRepository.listarDetalleProductoAsignacion(sesion, planta);								
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar el detalle Producto Asignacion", ex.getMessage());
		} finally {
			sesion.close();
		}
	}
	
	

}
