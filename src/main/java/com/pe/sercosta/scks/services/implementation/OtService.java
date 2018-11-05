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
import com.pe.sercosta.scks.entities.Contenido;
import com.pe.sercosta.scks.entities.Muestra;
import com.pe.sercosta.scks.entities.Muestreo;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.models.LoteOtModel;
import com.pe.sercosta.scks.repositories.ILoteRepository;
import com.pe.sercosta.scks.repositories.IMuestraRepository;
import com.pe.sercosta.scks.services.IOtService;

@Service("ordenVentaService")
public class OtService implements IOtService {

	private static final Log LOG = LogFactory.getLog(OrdenVentaService.class);
	private static final String CAPA = "[Service : OrdenVenta] -> ";

	@PersistenceContext
	private EntityManager sesion;

	@Autowired
	@Qualifier("MuestraRepository")
	private IMuestraRepository muestraRepository;
	
	@Autowired
	@Qualifier("LoteRepository")
	private ILoteRepository loteRepository;
   

	@Override
	public List<Muestra> listarMuestraOt(Planta planta) {
		try {
			return muestraRepository.listarMuestraOt(sesion, planta);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las muestras", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public Muestra obtenerMuestraOt(Muestra muestra) {
		try {
			return muestraRepository.obtenerMuestraOt(sesion, muestra);
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al obtener la muestra", ex.getMessage());
		} finally {
			sesion.close();
		}
	}

	@Override
	public void actualizarMuestraOt(Muestra muestra) {
		try {
			validarActualizarMuestra(muestra); 
			muestraRepository.actualizarMuestarOt(sesion, muestra);
			loteRepository.listarLoteOt(sesion, muestra).forEach(a -> {
				LoteOtModel lote=new LoteOtModel();
				lote.setIdPresentacion(a.getIdPresentacion());
				lote.setIdLote(a.getIdLote());
				lote.setCantidadMuestreado(a.getCantidadMuestreado());
				//TODO embarcar 
			});
		} catch (SercostaException sx) {
			LOG.error(CAPA + "Usuario: " + sx.getMensajeUsuario());
			LOG.error(CAPA + "Aplicación: " + sx.getMensajeAplicacion());
			throw sx;
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al registrar el ot", ex.getMessage());
		} finally {
			sesion.close();
		}
		
	}
	
	public void validarActualizarMuestra(Muestra muestra) throws Exception {
		if(muestra.getOt().isEmpty() || muestra.getOt() == null )
			throw new Exception("El idOt es requerido.");
		if (muestra.getFechaMuestreado() == null)
			throw new Exception("La Fecha de OT es requerido.");
	}
}
