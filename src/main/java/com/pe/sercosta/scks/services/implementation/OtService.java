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
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.exceptions.SercostaException;
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
		// TODO Auto-generated method stub
		
	}

	

}
