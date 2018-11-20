package com.pe.sercosta.scks.services.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Asignacion;
import com.pe.sercosta.scks.entities.AsignacionPK;
import com.pe.sercosta.scks.entities.Cliente;
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.OrdenVenta;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.InfoOrdenVentaModel;
import com.pe.sercosta.scks.repositories.IAsignacionRepository;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
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
	
	@Autowired
	@Qualifier("contenidoRepository")
	private IContenidoRepository contenidoRepository;

	@Override
	public List<OrdenVenta> listarOrdenVenta(Planta planta) {
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
	public OrdenVenta obtenerOrdenVenta(OrdenVenta orden) {
		try {
			return ordenVentaRepository.obtenerOrdenVenta(sesion, orden);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al obtener la orden de venta", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public void registrarOrdenVenta(OrdenVenta ordenVenta, List<Presentacion> listaPresentacion) {
		// EntityTransaction tx = sesion.getTransaction();
		// tx.begin();
		try {
			validarRegistrarOrdenVenta(ordenVenta);
			ordenVentaRepository.registrarOrdenVenta(sesion, ordenVenta);
			listaPresentacion.forEach(p -> {
				double cantidadTotal = p.getCantidadTotal();
				List<Contenido> lista = contenidoRepository.listarContenidos(sesion, ordenVenta.getIdPlanta(), p);
				for(Contenido c: lista) {
					double cantidadInsertar = 0;
					if(c.getCantidad() - c.getComprometido() <= cantidadTotal) {
						cantidadInsertar = c.getCantidad() - c.getComprometido();						
						cantidadTotal -= c.getCantidad() - c.getComprometido();
					} else {
						cantidadInsertar = cantidadTotal;
						cantidadTotal = 0;
					}
					asignacionRepository.registrarAsignacion(sesion, 
							new Asignacion(
									new AsignacionPK(c.getLote().getIdLote(), c.getPresentacion().getIdPresentacion(), ordenVenta.getIdOrdenVenta())
									, cantidadInsertar));
					if(cantidadTotal == 0) {
						break;
					}
				}
			});
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			// tx.rollback();
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
		try {
			validarActualizarOrdenVenta(ordenVenta);
			ordenVentaRepository.actualizarOrdenVenta(sesion, ordenVenta);
			asignacionRepository.listarAsignacion(sesion, ordenVenta).forEach(a -> {
				Asignacion asignacion = new Asignacion();
				asignacion.setContenido(new Contenido(a.getIdLote(), a.getIdPresentacion()));
				asignacion.setOrdenVenta(ordenVenta);
				asignacionRepository.embarcarAsignacion(sesion, asignacion);
			});
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el lote", ex.getMessage());
		} finally {
			sesion.close();
		}

	}

	private void validarRegistrarOrdenVenta(OrdenVenta ordenVenta) throws Exception {
		if (ordenVenta.getIdOrdenVenta().isEmpty() || ordenVenta.getIdOrdenVenta() == null)
			throw new Exception("El IdOrdenVenta es requerido.");
		if (ordenVenta.getIdPlanta().getIdPlanta() == null)
			throw new Exception("El idPlanta es requerido.");
		if (ordenVenta.getIdCliente().getIdCliente() == null)
			throw new Exception("El idCliente es requerido.");
		if (ordenVenta.getFechaAsignacion() == null)
			throw new Exception("La fechaAsignacion es requerida");
	}

	private void validarActualizarOrdenVenta(OrdenVenta ordenVenta) throws Exception {
		if (ordenVenta.getIdOrdenVenta().isEmpty() || ordenVenta.getIdOrdenVenta() == null)
			throw new Exception("El idOrdenVenta es requerido.");
		if (ordenVenta.getFechaEmbarque() == null)
			throw new Exception("La Fecha de Embarque es requerido.");
		if (ordenVenta.getHoraEmbarque() == null)
			throw new Exception("La Hora de Embarque es requerido.");
		if (ordenVenta.getPaisDestino() == null)
			throw new Exception("El País de Destino es requerido.");
	}

	@Override
	public List<InfoOrdenVentaModel> listarInforOrdenVenta(OrdenVenta ordenVenta, Cliente cliente) {
		try {
			return ordenVentaRepository.listarInfoOrdenVenta(sesion, ordenVenta, cliente);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los Info Orden Venta Model", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
