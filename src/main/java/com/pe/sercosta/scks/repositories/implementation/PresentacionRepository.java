package com.pe.sercosta.scks.repositories.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.pe.sercosta.scks.entities.Presentacion;
import com.pe.sercosta.scks.exceptions.SercostaException;
import com.pe.sercosta.scks.repositories.IPresentacionRepository;

@Repository("presentacionRepository")
public class PresentacionRepository implements IPresentacionRepository {

	private static final Log LOG = LogFactory.getLog(PresentacionRepository.class);
	private static final String CAPA = "[Repository : Presentacion] -> ";

	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> listarPresentacion(EntityManager sesion) {
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_listar_presentaciones");
			myquery.execute();
			List<Object[]> lista = myquery.getResultList();
			lista.forEach(o -> {
				Presentacion aux = new Presentacion();
				aux.setIdPresentacion((String) o[0]);
				aux.setDescripcion((String) o[1]);
				listaPresentacion.add(aux);
			});
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al listar las presentaciones", ex.getMessage());
		}
		return listaPresentacion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Presentacion> buscarPresentacion(EntityManager sesion, Presentacion presentacion) {
		List<Presentacion> listaPresentacion = new ArrayList<Presentacion>();
		try {
			StoredProcedureQuery myquery = sesion.createStoredProcedureQuery("sp_filtrar_presentaciones");
			myquery.execute();
			listaPresentacion = myquery.getResultList();
		} catch (Exception ex) {
			LOG.error(CAPA + ex.getMessage());
			throw new SercostaException("Hubo un error al buscar la presentación", ex.getMessage());
		}
		return listaPresentacion;
	}

}
