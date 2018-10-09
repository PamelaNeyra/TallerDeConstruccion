package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pe.sercosta.scks.entities.Lote;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.OrdenVentaModel;
import com.pe.sercosta.scks.models.views.OrdenVentaView;
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
	
	@Override
	public List<OrdenVentaView> listarOrdenVenta(Planta planta) {
		try {
			return ordenVentaRepository.listarOrdenVenta(sesion, planta);
		} catch (SercostaException sx) {
			throw sx;
		} catch (Exception ex) {
			throw ex;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrdenVenta> buscarOrdenVenta(OrdenVenta ordenVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarOrdenVenta(OrdenVenta ordenVenta) {
		try {
			validarActualizarOrdenVenta(ordenVenta);
			ordenVentaRepository.actualizarOrdenVenta(sesion,ordenVenta);			
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
	
	private void validarActualizarOrdenVenta(OrdenVenta ordenVenta) throws Exception {
		if(ordenVenta.getIdOrdenVenta().isEmpty() || ordenVenta.getIdOrdenVenta() == null)
			throw new Exception("El idOrdenVenta es requerido.");
		if(ordenVenta.getFechaEmbarque()== null)
			throw new Exception("la fecha de embarque es requerido");
		if(ordenVenta.getHoraEmbarque() == null)
			throw new Exception("La Hora de embarque es requerido");
		if(ordenVenta.getPaisDestino() == null)
			throw new Exception("El pais de destino es requerido");
	}


}
