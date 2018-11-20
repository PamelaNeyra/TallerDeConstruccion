package com.pe.sercosta.scks.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.entities.MuestreoPK;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.ProductoTerminado;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.InfoMuestraModel;
import com.pe.sercosta.scks.repositories.IContenidoRepository;
import com.pe.sercosta.scks.repositories.IMuestraRepository;
import com.pe.sercosta.scks.repositories.IMuestreoRepository;
import com.pe.sercosta.scks.services.IMuestraService;

@Service("muestraService")
public class MuestraService implements IMuestraService{

	private static final Log LOG = LogFactory.getLog(LoteService.class);
	private static final String CAPA = "[Service : Muestra] -> ";
		
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("muestraRepository")
	private IMuestraRepository muestraRepository;
	
	@Autowired
	@Qualifier("muestreoRepository")
	private IMuestreoRepository muestreoRepository;
	
	@Autowired
	@Qualifier("contenidoRepository")
	private IContenidoRepository contenidoRepository;
	
	@Override
	public void registrarMuestra(Muestra muestra, List<ProductoTerminado> listaProducto) {
		//EntityTransaction tx = sesion.getTransaction();
		boolean errorValidacion = false;
		//tx.begin();
		try {
			//TODO: Validaciones de O2 - Muestrear Contenido
				if(muestra.getIdPlanta() != null){
					if(muestra.getIdLaboratorio() != null) {
						muestraRepository.registrarMuestra(sesion, muestra);
						//Recuperar muestra
						listaProducto.forEach(p -> {
							contenidoRepository.listarContenidos(sesion, muestra.getIdPlanta(), p)
							.forEach(c -> {
								Muestreo muestreo = new Muestreo();
								muestreo.setMuestreoPK(new MuestreoPK(c.getLote().getIdLote(), 
										c.getPresentacion().getIdPresentacion(), 0));
								muestreo.setCantidad(c.getCantidad());
								muestreoRepository.registrarMuestreo(sesion, muestreo);
							});
						});
						//tx.commit();
					}else {
						errorValidacion = true;
					}
				}else {
					errorValidacion = true;
				}
			if(errorValidacion == true) {
				LOG.error("Error al validar los campos de la Muestra");
				throw new Exception();
			}
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al registrar la muestra", ex.getMessage());
		} finally {
			sesion.close();
		}
		
	}
	
	public void validarRegistrarMuestra(Muestra muestra) throws Exception{
			if(muestra.getIdMuestra() == null)
				throw new Exception("El idMuestra es requerido.");
			if(muestra.getIdPlanta() == null)
				throw new Exception("El idPlanta es requerido.");
			if(muestra.getIdLaboratorio() == null)
				throw new Exception("El idLaboratorio es requerido.");
			if(muestra.getFechaCreacion() == null)
				throw new Exception("La fecha de creacion es requerido.");
			if(muestra.getCantidadTotal() >= 0.0)
				throw new Exception("La fecha de creacion es requerido.");
	}

	@Override
	public List<Muestra> listarMuestras() {
		try {
			//Falta implementar la capa de datos
			//return muestraRepository.listarMuestras(sesion);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			//tx.rollback();
			throw new SercostaException("Hubo un error al listar las Muestras", ex.getMessage());
		} finally {
			sesion.close();
		}
		return null;
	}

	@Override
	public List<InfoMuestraModel> listarInfoMuestra(Planta planta, Muestra muestra) {
		try {
			return muestraRepository.listarInfoMuestra(sesion, planta, muestra);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar los Info Muestra Model", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

}
