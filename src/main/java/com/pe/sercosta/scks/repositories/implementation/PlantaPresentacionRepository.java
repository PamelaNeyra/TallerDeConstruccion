package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Planta;
import com.pe.sercosta.scks.entities.PlantaPresentacion;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IPlantaPresentacionRepository;

@Repository("plantaPresentacionRepository")
public class PlantaPresentacionRepository implements IPlantaPresentacionRepository{

	private static final Log LOG = LogFactory.getLog(PlantaPresentacionRepository.class);
	private static final String CAPA = "[Repository : PlantaPresentacion] -> ";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PlantaPresentacion> listarPresentacion(EntityManager sesion, Planta planta) {
		List<PlantaPresentacion> listaPresentacion = new ArrayList<PlantaPresentacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_planta_presentaciones");
			myquery.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			myquery.setParameter(1, planta.getIdPlanta());
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				PlantaPresentacion aux = new PlantaPresentacion();
				Presentacion pre = new Presentacion();
				pre.setIdPresentacion((String) o[0]);
				pre.setDescripcion((String) o[1]);
				aux.setComprometidoTotal((Double) o[2]);
				aux.setCantidadTotal((Double) o[3]);
				aux.setPresentacion(pre);
				listaPresentacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		}
		return listaPresentacion;
	}

}
