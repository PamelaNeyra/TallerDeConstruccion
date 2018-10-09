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
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.views.OrdenVentaView;
import com.pe.sercosta.scks.repositories.IAsignacionRepository;
import com.pe.sercosta.scks.repositories.IOrdenVentaRepository;
import com.pe.sercosta.scks.services.IOrdenVentaService;

@Service("ordenVentaService")
public class OrdenVentaService implements IOrdenVentaService {

	private static final Log LOG = LogFactory.getLog(OrdenVentaService.class);
	private static final String CAPA = "[Service : OrdenVenta] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("ordenVentaRepository")
	private IOrdenVentaRepository ordenVentaRepository;
	
	@Autowired
	@Qualifier("asignacionRepository")
	private IAsignacionRepository asignacionRepository;
	
	@Override
	public List<OrdenVentaView> listarOrdenVenta(Planta planta) {
		try {
			return ordenVentaRepository.listarOrdenVenta(sesion, planta);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las órdenes de venta", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public OrdenVentaModel obtenerOrdenVenta(OrdenVenta orden) {
		try {
			return ordenVentaRepository.obtenerOrdenVenta(sesion, orden);
		} catch (SercostaException sx) {
			throw sx;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void registrarOrdenVenta(OrdenVenta ordenVenta) {
		//EntityTransaction tx = sesion.getTransaction();
		//tx.begin();
		try {
			validarRegistrarOrdenVenta(ordenVenta);
			ordenVentaRepository.registrarOrdenVenta(sesion, ordenVenta);
			ordenVenta.getAsignacionList().forEach(a -> asignacionRepository.registrarAsignacion(sesion, a));
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al registrar la orden de venta", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public List<OrdenVenta> buscarOrdenVenta(OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarOrdenVenta(OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		
	}
	
	private void validarRegistrarOrdenVenta(OrdenVenta ordenVenta) throws Exception {
		if(ordenVenta.getIdOrdenVenta().isEmpty() || ordenVenta.getIdOrdenVenta() == null)
			throw new Exception("El IdOrdenVenta es requerido.");
		if(ordenVenta.getIdPlanta().getIdPlanta() == null)
			throw new Exception("El idPlanta es requerido.");
		if(ordenVenta.getIdCliente().getIdCliente() == null)
			throw new Exception("El idCliente es requerido.");
		if(ordenVenta.getFechaAsignacion() == null)
			throw new Exception("La fechaAsignacion es requerida");
		if(ordenVenta.getAsignacionList() == null || ordenVenta.getAsignacionList().isEmpty())
			throw new Exception("La lista de asignaciones es requerida.");
	}

}
