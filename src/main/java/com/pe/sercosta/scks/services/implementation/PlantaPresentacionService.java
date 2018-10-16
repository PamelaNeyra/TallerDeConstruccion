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
import com.pe.sercosta.scks.entities.PlantaPresentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IPlantaPresentacionRepository;
import com.pe.sercosta.scks.services.IPlantaPresentacionService;

@Service("plantaPresentacionService")
public class PlantaPresentacionService implements IPlantaPresentacionService {

	private static final Log LOG = LogFactory.getLog(PlantaPresentacionService.class);
	private static final String CAPA = "[Service : PlantaPresentacion] -> ";
	
	@PersistenceContext
    private EntityManager sesion;
	
	@Autowired
	@Qualifier("plantaPresentacionRepository")
	private IPlantaPresentacionRepository plantaPresentacionRepository;
	
	@Override
	public List<PlantaPresentacion> listarPresentacion(Planta planta) {
		try {
			return plantaPresentacionRepository.listarPresentacion(sesion, planta);
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

	
}
